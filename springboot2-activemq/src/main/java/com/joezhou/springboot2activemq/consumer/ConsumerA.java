package com.joezhou.springboot2activemq.consumer;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @author JoeZhou
 */
@Component
public class ConsumerA {

    @JmsListener(destination = "start.queue")
    public void spendFromQueue(ActiveMQTextMessage msg) {
        try {
            System.out.println("consumerA spend: " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}