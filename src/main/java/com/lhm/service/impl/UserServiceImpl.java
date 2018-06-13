package com.lhm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lhm.dao.UserMapper;
import com.lhm.model.User;
import com.lhm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String  USER_KEY = "USER_";//存储用户的rediskey

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public List<User> listUsers(Map<String, Object> param) {
        return userMapper.listUsers(param);
    }

    @Override
    public int countRows() {
        return userMapper.countRows();
    }

    @Override
    public User getUserById(String id) {
        // 从缓存中获取用户信息
        String key = USER_KEY + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            logger.info("从缓存中获取了用户 id = " + id);
            return user;
        }

        // 缓存不存在，从 DB 中获取
        User user = userMapper.getUserById(id);
        logger.info("从DB获取了用户 id = " + id);
        // 插入缓存
        operations.set(key, user, 10, TimeUnit.SECONDS);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
        String key = USER_KEY + user.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("更新用户时候，从缓存中删除用户 >> " + user.getId());
        }
    }

    @Override
    public void deleteByIds(List<String> ids) {
        userMapper.deleteByIds(ids);
        for (String id : ids) {
            String key = USER_KEY + id;
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                logger.info("删除用户时候，从缓存中删除用户 >> " + id);
            }
        }
    }


}
