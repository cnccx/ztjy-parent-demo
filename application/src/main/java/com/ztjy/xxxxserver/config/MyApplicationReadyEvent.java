package com.ztjy.xxxxserver.config;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: 监听器</p>
 *
 * <p>Copyright: © 2018-2018 北京掌通未来科技有限公司.All rights reserved.</p>
 *
 * @author zhangjiaxing
 *
 * @date 2018-04-20 15:57:26
 **/
@Log4j2
public class MyApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initOns(applicationReadyEvent);
    }

    /**
     * 初始化并启动Ons
     * @param applicationReadyEvent 应用准备完毕事件
     */
    private void initOns(ApplicationReadyEvent applicationReadyEvent) {
        ConfigurableApplicationContext cac = applicationReadyEvent.getApplicationContext();
        ConsumerBean consumer = (ConsumerBean) cac.getBean("consumerBean");
        ProducerBean producer = (ProducerBean) cac.getBean("producerBean");

        // 启动消费者
        Subscription subscription = new Subscription();
        subscription.setTopic("schoolFeeds_debug");
        MyListener myListener = new MyListener();
        Map<Subscription, MessageListener> map = new HashMap<>(10);
        map.put(subscription, myListener);
        consumer.setSubscriptionTable(map);
        consumer.start();

        // 启动生产者
        producer.start();
    }
}
