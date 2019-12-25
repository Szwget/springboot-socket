package com.szw.springbootsocket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 14:22
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "socketio")
@Component
@Data
public class SocketConfig {
    private int port;

    private int linkedCount;

    private Boolean allowRequest;

    private Integer upgradeTimeOut;

    private Integer pingTimeOut;

    private Integer pingSpace;

    private Integer contextLength;

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
