package com.szw.springbootsocket.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 11:37
 * @Version 1.0
 */
@Slf4j
public class AppConnectListener implements ConnectListener {

    @Autowired
    private SocketIOServer server;

    public AppConnectListener (SocketIOServer server){
        this.server = server;
    }

    @Override
    public void onConnect(SocketIOClient client) {
        log.info("{}({}) 进入连接...",client.getSessionId(),client.getRemoteAddress());
    }
}
