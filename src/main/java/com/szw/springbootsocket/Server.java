package com.szw.springbootsocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.szw.springbootsocket.bean.MessageBean;
import com.szw.springbootsocket.bean.RoomBean;
import com.szw.springbootsocket.listener.AppConnectListener;
import com.szw.springbootsocket.listener.AppDisconnectListener;
import com.szw.springbootsocket.listener.RoomListener;
import com.szw.springbootsocket.listener.SendMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 13:50
 * @Version 1.0
 */
@Component
@Order(1)
@Slf4j
public class Server implements CommandLineRunner {

    private final SocketIOServer server;

    @Autowired
    public Server(SocketIOServer socketIOServer) {
        this.server = socketIOServer;
    }

    public void init() {

        // 连接监听器
        server.addConnectListener(new AppConnectListener(server));
        // 断开连接监听器
        server.addDisconnectListener(new AppDisconnectListener(server));
        // 消息发送监听器
        server.addEventListener(Config.CHATEVENT, MessageBean.class, new SendMessageListener(server));
        server.addEventListener(Config.ROOMEVENT, RoomBean.class, new RoomListener(server));
        // 服务启动
        log.info("socket服务准备启动");
        server.startAsync();
        log.info("socket启动完成");
    }

    @Override
    public void run(String... args) throws Exception {
        this.init();
    }

}
