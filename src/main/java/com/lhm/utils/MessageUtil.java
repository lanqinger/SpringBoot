package com.lhm.utils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * 返回消息提示
 */
public class MessageUtil {

    /***
     * 公用设置返回json格式的失败信息
     * @param mess：提示信息
     * @return
     */
    public static String setComFailMess(String mess){
        return "{\"result\":\"fail\",\"message\":\""+mess+"\"}";
    }

    /***
     * 公用设置返回的json格式的成功信息
     * @param mess：提示信息
     * @return
     */
    public static String setComSuccessMess(String mess){
        return "{\"result\":\"success\",\"message\":\""+mess+"\"}";
    }

    /**
     * 返回成功
     * @param obj
     * @return
     */
    public static String success(Object obj) {
        JSONObject json = new JSONObject();
        json.put("result", "success");
        json.put("data", obj);
        return json.toString();
    }

    /**
     * 返回失败
     * @param obj
     * @return
     */
    public static String fail(String obj) {
        JSONObject json = new JSONObject();
        json.put("result", "fail");
        json.put("message", obj);
        return json.toString();
    }

}
