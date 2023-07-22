package com.phongtq.brettspiel.socket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.phongtq.brettspiel.socket.model.SocketModel;
import com.phongtq.brettspiel.socket.service.ISocketIOMessageService;
import com.phongtq.brettspiel.socket.service.ISocketIOPubSubService;
import com.phongtq.brettspiel.socket.service.ISocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * Created by Quach Thanh Phong
 * On 7/3/2023 - 1:46 PM
 */
@Slf4j
@Controller
public class TestHandler extends BaseHandler<SocketModel<?>> {

    public TestHandler(SocketIOServer socketIOServer, ISocketIOService socketIOService, ISocketIOMessageService socketIOMessageService, ISocketIOPubSubService socketIOPubSubService) {
        super(socketIOServer, socketIOService, socketIOMessageService, socketIOPubSubService);
    }

    @OnEvent("test")
    @Override
    public void onData(SocketIOClient socketIOClient, SocketModel<?> socketModel, AckRequest ackRequest) {
        log.info("TEST {}", socketModel);
    }

    @OnConnect
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        log.info("TEST connect");
    }

    @OnDisconnect
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("TEST disconnect");
    }

}
