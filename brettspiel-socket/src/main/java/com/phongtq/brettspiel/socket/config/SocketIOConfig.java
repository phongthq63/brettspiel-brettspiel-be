package com.phongtq.brettspiel.socket.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.phongtq.brettspiel.socket.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Quách Thanh Phong
 * On 7/3/2023 - 12:26 AM
 */
@Configuration
public class SocketIOConfig {

    @Value("${socket-io.context}")
    private String context;

    @Value("${socket-io.port}")
    private Integer port;

    @Value("${socket-io.max-frame-payload-length}")
    private int maxFramePayloadLength;

    @Value("${socket-io.max-http-content-length}")
    private int maxHttpContentLength;

    @Value("${socket-io.boss-count}")
    private int bossCount;

    @Value("${socket-io.work-count}")
    private int workCount;

    @Value("${socket-io.allow-custom-requests}")
    private boolean allowCustomRequests;

    @Value("${socket-io.upgrade-timeout}")
    private int upgradeTimeout;

    @Value("${socket-io.ping-timeout}")
    private int pingTimeout;

    @Value("${socket-io.ping-interval}")
    private int pingInterval;

    @Autowired
    private ExceptionListener exceptionListener;

    @Autowired
    private LoginHandler loginHandler;


    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
//        socketConfig.setReuseAddress(false);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setOrigin("*:*");
        config.setPort(port);
        config.setTransports(Transport.POLLING, Transport.WEBSOCKET);
        config.setContext(context);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
//        config.setUseLinuxNativeEpoll(true);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        config.setMaxHttpContentLength(maxHttpContentLength);
        config.setMaxFramePayloadLength(maxFramePayloadLength);
//        config.setStoreFactory(new RedissonStoreFactory(redissonClient));
        config.setExceptionListener(exceptionListener);
        config.setAuthorizationListener(loginHandler);
        config.setAddVersionHeader(true);
        config.setHttpCompression(true);
        config.setWebsocketCompression(true);
        config.setSocketConfig(socketConfig);

        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}
