package com.joezhou.springboot2.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoeZhou
 */
@RestController
@RequestMapping("api/websocket")
public class WebSocketController {

    private WebSocketServer webSocketServer;

    @Autowired
    public WebSocketController(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @RequestMapping("send-to-one")
    public String sendToOne(String id, String msg) {
        webSocketServer.sendById(msg, id);
        return "向用户" + id + "发送消息-成功！";
    }


    @RequestMapping("/send-to-all.action")
    public String sendToAll(String msg) {
        webSocketServer.sendToAll(msg);
        return "向全部用户发送消息-成功！";
    }
}