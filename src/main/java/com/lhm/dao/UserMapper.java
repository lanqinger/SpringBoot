package com.lhm.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lhm.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    //@Select("SELECT id, account_number as accountNumber, password, create_date as createDate FROM user_info ")
    List<User> listUsers(Map<String, Object> param);

    //@Select("SELECT count(1) FROM user_info ")
    int countRows();

    User getUserById(String id);

    void updateUser(User user);

    void deleteByIds(List<String> ids);


}
