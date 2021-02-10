package com.joezhou.springboot2rocketmq.controller;

import com.joezhou.springboot2rocketmq.producer.ProducerA;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/rocketmq")
public class RocketMqController {

    private ProducerA producerA;

    @Autowired
    public RocketMqController(ProducerA producerA) {
        this.producerA = producerA;
    }

    @RequestMapping("send")
    public void send(String topic, String tag, String msg) throws UnsupportedEncodingException, InterruptedException,
            RemotingException, MQClientException, MQBrokerException {

        Message message = new Message(topic, tag, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult result = producerA.getProducer().send(message);
        System.out.println("msg-id: " + result.getMsgId());
        System.out.println("status: " + result.getSendStatus());
    }
}