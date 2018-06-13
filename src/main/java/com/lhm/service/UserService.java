package com.lhm.service;

import com.lhm.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> listUsers(Map<String, Object> param);

    int countRows();

    User getUserById(String id);

    void updateUser(User user);

    void deleteByIds(List<String> ids);
}
