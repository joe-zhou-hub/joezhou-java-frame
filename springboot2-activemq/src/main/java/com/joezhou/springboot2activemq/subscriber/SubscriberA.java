package com.joezhou.springboot2activemq.subscriber;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @author JoeZhou
 */
@Component
public class SubscriberA {

    @JmsListener(destination = "start.topic")
    public void spendFromTopic(ActiveMQTextMessage msg) {
        try {
            System.out.println("subscriberA spend: " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}