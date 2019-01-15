package com.lhm;

import com.lhm.model.User;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        System.out.println(json);
        assertEquals("success", json.getString("result"));
    }

    @Test
    public void update() {
        //post json请求
        String endpoint = "update.do";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        httpEntity = new HttpEntity<>(headers);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("password", "123456");
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(jsonObject, httpEntity.getHeaders());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + endpoint, request, String.class);
        String result = responseEntity.getBody();
        JSONObject json = JSONObject.fromObject(result);
        System.out.println(json);
        assertEquals("success", json.getString("result"));
    }

    @Test
    public void delete() {
        //删除市场定价 POST请求
        String endpoint = "delete.do?ids={ids}";
        Map map = new HashMap();
        List<String> ids = new ArrayList<>();
        ids.add("11");
        map.put("ids", ids);
        ResponseEntity<String> responseEntity = restTemplate.exchange(host + endpoint, HttpMethod.POST, httpEntity, String.class, map);
        JSONObject json = JSONObject.fromObject(responseEntity.getBody());
        assertEquals("success", json.getString("result"));
    }


}
