package com.joezhou.springboot2.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JoeZhou
 */
@ServerEndpoint("/websocket-server/{id}")
@Component
public class WebSocketServer {

    /**
     * 用于存储每个BS用户的WebSocketServer实例
     * ConcurrentHashMap保证线程安全，static保证实例唯一
     */
    private static Map<String, WebSocketServer> servers = new ConcurrentHashMap<>();
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        this.session = session;

        servers.put(id, this);
        System.out.printf("cli-%s login, total %d clients...\n", id, servers.size());
        sendToAll("cli-" + id + " login...");
    }

    @OnClose
    public void onClose(@PathParam("id") String id, Session session) {
        servers.remove(id);
        System.out.printf("cli-%s logoff, total %d clients...\n", id, servers.size());
        sendToAll("cli-" + id + " logoff...");
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, String msg, Session session) {
        sendToAll("cli-" + id + " say: " + msg);
    }

    @OnError
    public void onError(@PathParam("id") String id, Throwable e, Session session) {
        System.out.println("cli-" + id + " error...");
        e.printStackTrace();
    }

    private void sendToAll(String msg){
        for (String key : servers.keySet()) {
            servers.get(key).session.getAsyncRemote().sendText(msg);
        }
    }
}