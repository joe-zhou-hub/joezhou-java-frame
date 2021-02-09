package com.joezhou.springboot2activemq.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author JoeZhou
 */
@Component
public class ProducerA {

    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    public ProducerA(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public void sendToQueue(String queueName, final Object msg) {
        Destination dest = new ActiveMQQueue(queueName);
        jmsMessagingTemplate.convertAndSend(dest, msg);
    }
}