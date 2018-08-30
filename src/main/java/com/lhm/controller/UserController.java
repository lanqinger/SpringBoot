package com.lhm.controller;

import com.lhm.model.User;
import com.lhm.service.UserService;
import com.lhm.utils.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *  默认返回JSON字符串
 */
@Api(value = "UserController", description = "用户管理")
@RestController
@RequestMapping(value="/user",produces="text/plain;charset=utf-8")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户列表")
    @GetMapping(value="getUserList.do")
    public String index(User user) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<User> userList = userService.listUsers(param);
        int count = userService.countRows();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userList", userList);
        jsonObject.put("count", count);
        return jsonObject.toString();
    }

    @GetMapping(value="getUserById.do")
    @ApiOperation(value = "根据用户ID获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "string",paramType = "query"),
    })
    public String getUserById(String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return MessageUtil.fail("用户不存在存在");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        return MessageUtil.success(jsonObject);
    }

    @PostMapping(value = "update.do")
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "string",paramType = "query"),
    })
    public String update(User user) {
        try {
            userService.updateUser(user);
            user = userService.getUserById(user.getId());
        } catch (Exception e) {
            logger.error("修改用户信息异常：" + e.getMessage());
            return MessageUtil.fail("修改用户信息失败");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        return MessageUtil.success(jsonObject);
    }

    @PostMapping(value = "delete.do")
    @ApiOperation(value = "批量删除用户信息")
    public String delete(@RequestParam(required=true)List<String> ids) {
        try {
            userService.deleteByIds(ids);
            return MessageUtil.setComSuccessMess("删除成功");
        } catch (Exception e) {
            logger.error("用户删除异常：" + e.getMessage());
            return MessageUtil.setComFailMess("删除失败");
        }
    }

    @PostMapping(value = "uploadImg.do")
    @ApiOperation(value = "上传图片")
    public String uploadImg(@RequestParam(required = false) MultipartFile file, MultipartHttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {

        } catch (Exception e) {
            logger.error("用户上传图片异常：" + e.getMessage());
            return MessageUtil.setComFailMess("上传失败");
        }
        return MessageUtil.setComFailMess("上传成功！");
    }

}
