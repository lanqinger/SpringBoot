package com.lhm.service.impl;

import com.lhm.dao.UserMapper;
import com.lhm.model.User;
import com.lhm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers(Map<String, Object> param) {
        return userMapper.listUsers(param
        );
    }

    @Override
    public int countRows() {
        return userMapper.countRows();
    }
}
