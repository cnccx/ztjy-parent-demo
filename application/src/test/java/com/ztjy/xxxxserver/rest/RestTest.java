package com.ztjy.xxxxserver.rest;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGet() throws URISyntaxException {
        String res = restTemplate.getForEntity(new URI("http://www.baidu.com:80/"), String.class).getBody();
        System.out.println(res);
    }

    @Test
    public void testPostWithHeaders() {
        // 这里还没有测试成功 TODO
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置header
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> params = new HashMap<>(20);
        params.put("aaa", "bbb");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(
                params, httpHeaders);
        ResponseEntity<JSONObject> resp = restTemplate.exchange("", HttpMethod.POST, requestEntity, JSONObject.class);
        JSONObject res = resp.getBody();
        System.out.println(res);
    }

    @Test
    public void testPostForm() {
        String url = "http://www.baidu.com";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
    }
}
