package com.joezhou.springboot2activemq.controller;

import com.joezhou.springboot2activemq.producer.ProducerA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
    public Object sendToQueue(String msg) throws InterruptedException {
        for (int i = 0, j = 10; i < j; i++) {
            TimeUnit.SECONDS.sleep(1L);
            producerA.sendToQueue("start.queue", msg + "-" + i);
        }
        return "sendToQueue() success";
    }
}