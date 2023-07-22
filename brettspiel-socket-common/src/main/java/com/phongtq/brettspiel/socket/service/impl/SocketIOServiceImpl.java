package com.phongtq.brettspiel.socket.service.impl;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.EventInterceptor;
import com.corundumstudio.socketio.transport.NamespaceClient;
import com.phongtq.brettspiel.socket.service.ISocketIOClientService;
import com.phongtq.brettspiel.socket.service.ISocketIOPubSubService;
import com.phongtq.brettspiel.socket.service.ISocketIOService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Quach Thanh Phong
 * On 7/3/2023 - 10:32 AM
 */
@Slf4j
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnBean(SocketIOServer.class)
public class SocketIOServiceImpl implements ISocketIOService, EventInterceptor {

    private static final Map<String, Map<UUID, SocketIOClient>> clientMap = new ConcurrentHashMap<>();

    private boolean cluster;

    private SocketIOServer socketIOServer;

    private final ISocketIOClientService socketIOClientService;

    private final ISocketIOPubSubService socketIOPubSubService;



    public SocketIOServiceImpl(SocketIOServer socketIOServer,
                               ISocketIOClientService socketIOClientService,
                               ISocketIOPubSubService socketIOPubSubService,
                               @Value("${socket-io.cluster:#{false}}") boolean cluster) {
        this.cluster = cluster;
        this.socketIOServer = socketIOServer;
        this.socketIOClientService = socketIOClientService;
        this.socketIOPubSubService = socketIOPubSubService;
    }

    @PostConstruct
    public void autoStartup() {
        start();
    }

    @PreDestroy
    public void autoStop() {
        stop();
    }


    @Override
    public void start() {
        socketIOServer.addConnectListener(socketIOClientService);
        socketIOServer.addDisconnectListener(socketIOClientService);
        socketIOServer.addEventInterceptor(this);

        socketIOServer.start();
        if (cluster) {
            socketIOPubSubService.initPubSubStoreListener();
        }
    }

    @Override
    public void stop() {
        if (cluster) {
            socketIOPubSubService.removePubSubStoreListener();
        }
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public boolean isCluster() {
        return cluster;
    }

    @Override
    public void onEvent(NamespaceClient namespaceClient, String s, List<Object> list, AckRequest ackRequest) {
        log.info("SocketIO - EventInterceptor: {} {} {} {}", namespaceClient.getSessionId(), s, list, ackRequest);
    }
}
