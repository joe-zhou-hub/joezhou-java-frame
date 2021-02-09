package com.joezhou.springboot2websocket.sse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/sse")
public class SseController {

    @RequestMapping(value = "test", produces = "text/event-stream;charset=UTF-8")
    public String test() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:" + Math.random() + "\n\n";
    }
}