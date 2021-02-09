package com.joezhou.springboot2activemq.controller;

import com.joezhou.springboot2activemq.producer.ProducerA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/producer")
public class ProducerController {

    private ProducerA producerA;

    @Autowired
    public ProducerController(ProducerA producerA) {
        this.producerA = producerA;
    }

    @RequestMapping("send-to-queue")
    public Object sendToQueue(String msg) {
        producerA.sendToQueue("start.queue", msg);
        return "success";
    }
}