package com.ztjy.xxxxserver.controller;

import com.ztjy.common.token.IgnoreToken;
import com.ztjy.datasource.MultipleDataSourceUtil;
import com.ztjy.xxxxserver.exception.ServerNameException;
import com.ztjy.xxxxserver.exception.ServerNameExceptionEnum;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.ztjy.xxxxserver.model.BoyVo;
import com.ztjy.xxxxserver.service.impl.RabbitmqServiceImpl;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@Log4j2
@IgnoreToken
class HelloController {
    @Autowired
    private ProducerBean producerBean;

    @Autowired
    RabbitmqServiceImpl rabbitmqServiceImpl;

    @Autowired
    private MultipleDataSourceUtil multipleDataSourceUtil;

    @RequestMapping("/exception")
    public String exception() {
        BoyVo boyVo = new BoyVo();
        boyVo.setName("wang");
        throw new ServerNameException(ServerNameExceptionEnum.BOY_AGE_ERROR);
    }

    @RequestMapping("/hello")
    public Object hello() {
        log.info("hello------");
        return "hello world";
    }

    @RequestMapping("/redirect")
    public Object redirect() {
        log.info("redirect------");
        RedirectView redirectView = new RedirectView("http://www.baidu.com");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @RequestMapping("/multi-datasource")
    @IgnoreToken
    public String multiDatasource() {
        List<Map<String,Object>> list=new ArrayList<>();
        String sql1 = "SELECT * FROM boy WHERE id = 1";
        list.add(multipleDataSourceUtil.getJdbcTemplate("testdb").queryForMap(sql1));

        String sql2 = "SELECT * FROM boy WHERE id = 1";
        list.add(multipleDataSourceUtil.getJdbcTemplate("test_db").queryForMap(sql2));
        return list.toString();
    }

    @RequestMapping("/ons")
    public Object helloOns() {
        Message message = new Message();
        message.setTopic("schoolFeeds_debug");
        message.setBody("haha".getBytes());
        producerBean.sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.toString());
            }

            @Override
            public void onException(OnExceptionContext onExceptionContext) {
                log.info(onExceptionContext.toString());
            }
        });
        return producerBean.getProperties();
    }
}