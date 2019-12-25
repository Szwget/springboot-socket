package com.szw.springbootsocket.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.szw.springbootsocket.Config;
import com.szw.springbootsocket.bean.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 14:03
 * @Version 1.0
 */
@Component
@Slf4j
public class SendMessageListener implements DataListener<MessageBean> {

    @Autowired
    private SocketIOServer server;

    public SendMessageListener (SocketIOServer server){
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient client, MessageBean data, AckRequest ackSender) throws Exception {

        String roomName = data.getRoomName();
        String option = data.getOption();
        BroadcastOperations bo = server.getRoomOperations(roomName);
        if ("send".equals(option)) {
            log.info("{}进入发送消息事件",client.getSessionId());
            bo.sendEvent(Config.TARGETEVENT, data);
        } else {
            log.info("{}进入错误分支",client.getSessionId());
            bo.sendEvent(Config.TARGETEVENT,"这是什么操作？？？");
        }
    }

}
