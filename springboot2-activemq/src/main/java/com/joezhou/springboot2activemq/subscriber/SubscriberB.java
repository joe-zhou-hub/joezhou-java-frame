package com.joezhou.springboot2activemq.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author JoeZhou
 */
@Component
public class SubscriberB {

    @JmsListener(destination = "start.topic")
    public void spendFromTopic(ActiveMQTextMessage msg) {
        try {
            System.out.println("subscriberB spend: " + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}