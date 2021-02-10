package com.joezhou.springboot2rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author JoeZhou
 */
@PropertySource("classpath:application.properties")
@Component
public class ProducerA {

    @Value("${joezhou.rocketmq.producer-group-name}")
    private String producerGroupName;

    @Value("${joezhou.rocketmq.name-srv-addr}")
    private String nameSrvAddr;

    private DefaultMQProducer producer;

    @PostConstruct
    public void init() {
        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(nameSrvAddr);
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