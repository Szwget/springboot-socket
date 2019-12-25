package com.szw.springbootsocket.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 11:40
 * @Version 1.0
 */
@Slf4j
public class AppDisconnectListener implements DisconnectListener {

    @Autowired
    private SocketIOServer server;

    public AppDisconnectListener (SocketIOServer server){
        this.server = server;
    }

    @Override
    public void onDisconnect(SocketIOClient client) {
        log.info("{} 断开连接.", client.getSessionId());
    }
}
