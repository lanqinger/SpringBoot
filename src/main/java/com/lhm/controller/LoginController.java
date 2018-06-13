package com.lhm.controller;

import com.lhm.model.User;
import com.lhm.service.UserService;
import com.lhm.utils.MessageUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 *
 */

@Api(value = "LoginController", description = "登录操作")
@RestController
@RequestMapping(value="/login",produces="text/plain;charset=utf-8")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /***
     * 用户登录
     * @param username：用户名
     * @param password：密码
     * @return
     */
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string",paramType = "query")
    })
    @GetMapping(value="userLogin.do")
    public String userLogin(String username, String password) {
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            return MessageUtil.setComFailMess("用户名密码不允许为空");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("accountNumber", username);
        param.put("password", password);
        try {
            List<User> userList = userService.listUsers(param);
            if (userList == null || userList.size() == 0) {
                return MessageUtil.setComFailMess("用户名密码不存在");
            }
        } catch (Exception e) {
            logger.error("用户登录异常：" + e.getMessage());
            return MessageUtil.setComFailMess("用户登录失败");
        }
        return MessageUtil.setComSuccessMess("登录成功");
    }
}
