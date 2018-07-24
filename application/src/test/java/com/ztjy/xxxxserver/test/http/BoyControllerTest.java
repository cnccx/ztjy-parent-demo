/**  
 * All rights Reserved, Designed By www.szy.cn
 * @Title  BoyControllerTest.java   
 * @Package com.ztjy.xxxx.test.http
 * @author 掌通家园
 * @date   2018年5月3日 下午6:49:46   
 * @Copyright 2014-现在  All rights reserved. 厦门神州鹰软件科技有限公司
 */
package com.ztjy.xxxxserver.test.http;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.log4j.Log4j2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ztjy.MyApplication;
import com.ztjy.common.response.ResultBody;
import com.ztjy.common.token.Payload;
import com.ztjy.common.utils.JsonUtils;
/**
 * @Title TODO 类描述
 * @Description TODO 详细描述
 * @ClassName BoyControllerTest
 * @Copyright 2014-现在 厦门神州鹰掌通家园项目组
 * @author 胡耀忠 huyaozhong
 * @date 2018年5月3日 下午6:49:46 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.JVM) 
@Log4j2
public class BoyControllerTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    /**
     * 设置接口超时时间.可设置长点，防止调试超时
     */
    @Rule  
    public Timeout timeout = new Timeout(1500,TimeUnit.SECONDS);  
	
    /**
     * 请求地址，对应文档定义
     */
    String url = "";
	Map<String,Object> body = new HashMap<String,Object>();
	ResponseEntity<ResultBody> result = null;
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void before(){
		
		Payload payload = new Payload();
		payload.setAppType("");
		payload.setDevType("Android");
		payload.setOperation(false);
		payload.setSessionId("sessionid");
		payload.setUserId("userid");
		
		/**
		 * 手动设置网关验证通过后的标识
		 */
		headers.add("verified", "1");
		/**
		 * 手动设置payload信息
		 */
		headers.add("payload", JsonUtils.getJson(payload));
    	
    }
	
	@After
	public void after(){
		
		result = restTemplate.exchange(url,HttpMethod.POST,new HttpEntity<Map<String,Object>>(body,headers),  ResultBody.class);
		log.info("协议{},请求结果:{}", url, JsonUtils.getJson(result));
		Assert.assertTrue("协议" + url + "请求失败",
				result.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue("协议" + url + "返回码不为10000,具体返回内容:"+JsonUtils.getJson(result.getBody()),
				"10000".equals(result.getBody().getCode()+""));
		
	}
    
    
    @Test
	public void hello(){
		
    	url = "/boy/auth/hello";
		
		
	}
	
	
	
}
