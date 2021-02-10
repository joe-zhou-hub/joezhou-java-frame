package com.joezhou.springboot2rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author JoeZhou
 */
@Component
public class ConsumerB {

    @PostConstruct
    public void init() {
        DefaultMQPushConsumer consumer;
        try {
            consumer = new DefaultMQPushConsumer("consumer-group-a");
            consumer.setNamesrvAddr("localhost:9876");
            consumer.subscribe("topic-a", "tag-a || tag-b");
            consumer.registerMessageListener((MessageListenerConcurrently) (messageExtList, context) -> {
                try {
                    for (MessageExt messageExt : messageExtList) {
                        System.out.println("ConsumerB spend: " + new String(messageExt.getBody()));
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