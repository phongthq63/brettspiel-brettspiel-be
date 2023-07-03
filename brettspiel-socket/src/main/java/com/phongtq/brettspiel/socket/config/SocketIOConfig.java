package com.phongtq.brettspiel.socket.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.phongtq.brettspiel.socket.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

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

    @Resource
    private ExceptionListener exceptionListener;

    @Resource
    private LoginHandler loginHandler;


    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
//        config.setUseLinuxNativeEpoll(true);
        config.setTransports(Transport.POLLING, Transport.WEBSOCKET);
        config.setOrigin("*:*");
        config.setPort(port);
        config.setContext(context);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
//        config.setStoreFactory(new RedissonStoreFactory(redissonClient));
        config.setExceptionListener(exceptionListener);
        config.setAuthorizationListener(loginHandler);
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}
