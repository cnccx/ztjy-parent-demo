package com.ztjy.xxxxserver.service.impl;

import org.springframework.stereotype.Service;

import com.ztjy.common.utils.RabbitmqReceiver;

/**
 * @program: parent-demo
 * @description: rabbit service
 * @author: zhangjiaxing
 * @create: 2018-03-02 15:12
 **/
@Service
public class RabbitmqServiceImpl implements RabbitmqReceiver {

  /*  private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired(required = false)
    private RabbitmqProperties rabbitmqProperties;

    @Autowired
    private RabbitmqSender rabbitmqSender;

    public Boolean processMessage(byte[] body) {
        return false;
    }

    public void sendMessage(Object object) {
      //  rabbitmqSender.sendMessage(rabbitmqProperties.getExchange(), object.toString());
      //  logger.info("send message " + object.toString() + " to exchange: " + rabbitmqProperties.getExchange());
    }*/
}
