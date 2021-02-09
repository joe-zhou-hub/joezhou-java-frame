package com.joezhou.springboot2activemq.publish;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author JoeZhou
 */
@Component
public class PublishA {

    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    public PublishA(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public void sendToTopic(String topicName, final Object msg) {
        Destination dest = new ActiveMQTopic(topicName);
        jmsMessagingTemplate.convertAndSend(dest, msg);
    }
}