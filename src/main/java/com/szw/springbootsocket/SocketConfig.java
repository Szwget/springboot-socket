package com.szw.springbootsocket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 14:22
 * @Version 1.0
 */
@Configuration
@Slf4j
public class SocketConfig {
    @Value("${nettySocketIO.port}")
    private int port;

    @Value("${nettySocketIO.linkedCount}")
    private int linkedCount;

    @Value("${nettySocketIO.allowRequest}")
    private Boolean allowRequest;

    @Value("${nettySocketIO.upgradeTimeOut}")
    private Integer upgradeTimeOut;

    @Value("${nettySocketIO.pingTimeOut}")
    private Integer pingTimeOut;

    @Value("${nettySocketIO.pingSpace}")
    private Integer pingSpace;

    @Value("${nettySocketIO.contextLength}")
    private Integer contextLength;

    @Value("${nettySocketIO.payloadLength}")
    private Integer payloadLength;


    @Bean("socketIOServer")
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();

        configuration.setPort(port);

        com.corundumstudio.socketio.SocketConfig socketConfig =  new com.corundumstudio.socketio.SocketConfig();
        socketConfig.setReuseAddress(true);
        configuration.setSocketConfig(socketConfig);
        configuration.setWorkerThreads(linkedCount);
        configuration.setAllowCustomRequests(allowRequest);
        configuration.setUpgradeTimeout(upgradeTimeOut);
        configuration.setPingTimeout(pingTimeOut);
        configuration.setPingInterval(pingSpace);
        configuration.setMaxHttpContentLength(contextLength);
        configuration.setMaxFramePayloadLength(payloadLength);
        return new SocketIOServer(configuration);
    }
}
