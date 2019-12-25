package com.szw.springbootsocket.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.szw.springbootsocket.Config;
import com.szw.springbootsocket.bean.RoomBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author szw
 * @Data 2019-12-25 13:53
 * @Version 1.0
 */
@Component
@Slf4j
public class RoomListener implements DataListener<RoomBean> {

    @Autowired
    private SocketIOServer server;

    public RoomListener(SocketIOServer server){
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient client, RoomBean data, AckRequest ackSender) throws Exception {

        String roomName = data.getRoomName();
        BroadcastOperations bo = server.getRoomOperations(roomName);
        String option = data.getOption();
        if("join".equals(option)){
            log.info("{}进入房间",client.getSessionId());
            //客户端加入房间
            client.joinRoom(data.getRoomName());
            //发送进入房间消息到所有在线的客户端
//            bo.sendEvent(Config.TARGETEVENT,data.getToken().concat("进入房间"));
            server.getClient(client.getSessionId()).sendEvent(Config.TARGETEVENT, "大美山西欢迎您");
        }else{
            log.info("{}走错了",client.getSessionId());
            server.getClient(client.getSessionId()).sendEvent(Config.TARGETEVENT, "走错了,小老弟~");
        }

    }
}
