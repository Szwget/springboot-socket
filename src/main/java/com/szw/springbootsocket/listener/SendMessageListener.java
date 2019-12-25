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

    /**
     * @Author szw
     * @Description  消息监听事件
     * @Date 2019-12-25
     * @Param [client, data, ackSender]
     * @return void
     **/
    @Override
    public void onData(SocketIOClient client, MessageBean data, AckRequest ackSender) throws Exception {

        String roomName = data.getRoomName();//获取房间唯一标识
        String option = data.getOption();//获取操作类型
        BroadcastOperations bo = server.getRoomOperations(roomName);
        if ("send".equals(option)) {
            log.info("{}进入发送消息事件",client.getSessionId());//调试时使用
            //TODO 存储消息内容到DB 其他业务逻辑代码
            //发送消息到指定房间，目前在线的所有用户可接收到数据,包括自己,如果自己发送的消息，无需服务器再次推送给自己
            //可以调用 bo.sendEvent(Config.TARGETEVENT,client,data);
            //此方法可以发送消息给除自己外的其他客户端
            bo.sendEvent(Config.TARGETEVENT, data);
        } else {
            log.info("{}进入错误分支",client.getSessionId());
            bo.sendEvent(Config.TARGETEVENT,"这是什么操作？？？");
        }
    }

}
