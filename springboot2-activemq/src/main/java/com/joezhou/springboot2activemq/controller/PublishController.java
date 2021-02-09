package com.joezhou.springboot2activemq.controller;

import com.joezhou.springboot2activemq.publish.PublishA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/publish")
public class PublishController {

    private PublishA publishA;

    @Autowired
    public PublishController(PublishA publishA) {
        this.publishA = publishA;
    }

    @RequestMapping("send-to-topic")
    public Object sendToTopic(String msg) {
        publishA.sendToTopic("start.topic", msg);
        return "success";
    }
}