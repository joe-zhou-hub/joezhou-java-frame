package com.joezhou.springboot2.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JoeZhou
 */
@ServerEndpoint("/websocket-server/{id}")
@Component
public class WebSocketServer {

    private static Map<String, WebSocketServer> webSocketServerMap = new ConcurrentHashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        this.session = session;
        webSocketServerMap.put(id, this);
        Map<String, String> pathParameters = session.getPathParameters();
        session.getRequestParameterMap();
        session.getAsyncRemote().sendText("连接成功！");
        System.out.println("用户连接：" + id);
        System.out.println("当前连接总人数：" + webSocketServerMap.size());
    }

    @OnClose
    public void onClose(@PathParam("id") String id, Session session) {
        webSocketServerMap.remove(id);
        System.out.println("用户登出：" + id);
        System.out.println("当前连接总人数：" + webSocketServerMap.size());
    }

    @OnError
    public void onError(Throwable e, Session session) {
        System.out.println("发生错误！");
        e.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    public void sendById(String msg, String id) {
        WebSocketServer userWebSocketServer = webSocketServerMap.get(id);
        if (userWebSocketServer != null) {
            userWebSocketServer.session.getAsyncRemote().sendText(msg);
        }
    }
    
    public void sendToAll(String msg) {
        Set<String> webSocketServerKeySet = webSocketServerMap.keySet();
        for (String key : webSocketServerKeySet) {
            WebSocketServer webSocketServer = webSocketServerMap.get(key);
            webSocketServer.session.getAsyncRemote().sendText(msg);
        }
    }
}