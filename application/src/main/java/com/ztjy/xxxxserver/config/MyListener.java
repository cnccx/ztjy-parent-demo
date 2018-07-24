package com.ztjy.xxxxserver.config;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
/**
 * /**
 * 
        *@author
 *
 */
public class MyListener implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext context) {
        System.out.println("Receive: " + new String(message.getBody()));
        try {
            //do something..
            return Action.ReconsumeLater;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
