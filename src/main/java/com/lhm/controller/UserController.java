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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
