package com.lhm.controller;

import com.lhm.model.User;
import com.lhm.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *  默认返回JSON字符串
 */
@RestController
@RequestMapping(value="/user",produces="text/plain;charset=utf-8")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value="getUserList.do")
    public String index() {

        Map<String, Object> param = new HashMap<String, Object>();
        List<User> userList = userService.listUsers(param);
        int count = userService.countRows();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userList", userList);
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @GetMapping(value="getUserById.do")
    public String getUserById() {
        JSONObject jsonObject = new JSONObject();
        User user = userService.getUserById("2");
        jsonObject.put("user", user);
        return jsonObject.toString();
    }

    @GetMapping(value = "update.do")
    public String update() {
        User user = userService.getUserById("2");
        user.setPassword("123456");
        userService.updateUser(user);
        User user2 = userService.getUserById("3");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createDate = dateTime.format(formatter);
        user2.setCreateDate(createDate);
        userService.updateUser(user2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user1", user);
        jsonObject.put("user2", user2);
        return jsonObject.toString();
    }

    @GetMapping(value = "delete.do")
    public String delete() {
        List<String> ids = new ArrayList<>();
        ids.add("4");
        ids.add("5");
        User user = userService.getUserById("4");
        User user2 = userService.getUserById("5");
        userService.deleteByIds(ids);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ids" ,ids);
        return jsonObject.toString();
    }

}
