package com.zq.project.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author zhangqian
 * @date 2023/2/20 15:39
 * @description: websocket
 */
@Slf4j
@Component
@ServerEndpoint("/test")
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session){
        log.info("客户端：{}连接成功",session.getId());
    }

    @OnClose
    public void onClose(Session session){
        log.info("客户端：{}断开连接",session.getId());
    }

    @OnMessage
    public String onMessage(String message,Session session){
        log.info("客户端：接收信息{}",message);
        return message;
    }

}
