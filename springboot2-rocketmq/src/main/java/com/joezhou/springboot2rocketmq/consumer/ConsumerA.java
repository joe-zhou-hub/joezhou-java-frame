package com.joezhou.springboot2rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author JoeZhou
 */
@PropertySource("classpath:application.properties")
@Component
public class ConsumerA {

    @Value("${joezhou.rocketmq.consumer-group-name}")
    private String consumerGroupName;

    @Value("${joezhou.rocketmq.name-srv-addr}")
    private String nameSrvAddr;

    @PostConstruct
    public void init() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroupName);
        consumer.setNamesrvAddr(nameSrvAddr);
        try {
            consumer.subscribe("topic-a", "tag-a || tag-b");
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {
                        System.out.println("ConsumerA spend: " + new String(messageExt.getBody()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}