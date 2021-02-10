package com.joezhou.springboot2rocketmq.controller;

import com.joezhou.springboot2rocketmq.producer.ProducerA;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String send(String topic, String tag, String msg) throws Exception {
        SendResult result = producerA.getProducer().send(
                new Message(topic, tag, msg.getBytes(RemotingHelper.DEFAULT_CHARSET)));
        System.out.println("msg-id: " + result.getMsgId());
        System.out.println("status: " + result.getSendStatus());
        return "success";
    }
}