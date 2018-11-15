package com.lhm;

import com.lhm.model.User;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpEntity<Map<String, Object>> httpEntity = null;

    String host = "http://localhost:8081/user/";

    @Before
    public void dealSession() {
        //设置登录用户信息
        /*String url = "http://localhost:8877/service/yyptback/allow/login/slogin.do";
        ResponseEntity<String> result = restTemplate.postForEntity(url, httpEntity, String.class);
        String SESSION = result.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders header = new HttpHeaders();
        header.add("Cookie",SESSION);//拼接放入Cookie
        httpEntity = new HttpEntity<>(header);*/
    }

    @Test
    public void getUserListTest() {
        //get请求
        String endpoint = "getUserList.do?accountNumber={accountNumber}&password={password}";
        Map map = new HashMap();
        map.put("accountNumber", "admin");
        map.put("password", "123");
        ResponseEntity<String> rEntity = restTemplate.exchange(host + endpoint, HttpMethod.GET, httpEntity, String.class, map);
        JSONObject json = JSONObject.fromObject(rEntity.getBody());
        assertEquals("success", json.getString("result"));
        System.out.println(rEntity.getStatusCode() + "," + rEntity.getStatusCodeValue());
    }

    @Test
    public void update() {
        //post请求
        User user = new User();
        user.setId("1");
        user.setPassword("123456");
        String endpoint = "update.do";
        Map map = new HashMap();
        map.put("user", user);
        ResponseEntity<User> rEntity = restTemplate.exchange(host + endpoint, HttpMethod.POST, httpEntity, User.class, user);
        JSONObject json = JSONObject.fromObject(rEntity.getBody().toString());
        assertEquals("success", json.getString("result"));

    }

}
