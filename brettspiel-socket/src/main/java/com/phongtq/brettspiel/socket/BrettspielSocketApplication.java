package com.phongtq.brettspiel.socket;

import com.corundumstudio.socketio.ClientOperations;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Quách Thanh Phong
 * On 7/2/2023 - 2:24 AM
 */
@Slf4j
@SpringBootApplication
public class BrettspielSocketApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BrettspielSocketApplication.class, args);

        //Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            private final SocketIOServer socketIOServer = applicationContext.getBean(SocketIOServer.class);

            @Override
            public void run() {
                log.info("ShutdownHook - Start - {}", socketIOServer);
                socketIOServer.getAllClients().forEach(ClientOperations::disconnect);
                log.info("ShutdownHook - End");
            }
        }));
    }

}
