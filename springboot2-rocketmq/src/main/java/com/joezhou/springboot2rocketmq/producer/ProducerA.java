package com.joezhou.springboot2rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author JoeZhou
 */
@Component
public class ProducerA {

    private DefaultMQProducer producer;

    @PostConstruct
    public void init() {
        producer = new DefaultMQProducer("producer-group-a");
        producer.setNamesrvAddr("localhost:9876");
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }
}