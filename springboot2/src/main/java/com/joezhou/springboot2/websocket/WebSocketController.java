package com.joezhou.springboot2.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("websocket")
public class WebSocketController {

    private WebSocketServer webSocketServer;

    @Autowired
    public WebSocketController(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @RequestMapping("send-to-user01.action")
    public String sendToUser01(String msg) {
        webSocketServer.sendById(msg, "1");
        return "向用户01发送消息-成功！";
    }

    @RequestMapping("/send-to-user02.action")
    public String sendToUser02(String msg) {
        webSocketServer.sendById(msg, "2");
        return "向用户02发送消息-成功！";
    }

    @RequestMapping("/send-to-all.action")
    public String sendToAll(String msg) {
        webSocketServer.sendToAll(msg);
        return "向全部用户发送消息-成功！";
    }
}