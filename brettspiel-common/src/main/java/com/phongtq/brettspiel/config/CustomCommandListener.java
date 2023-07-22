package com.phongtq.brettspiel.config;

import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 11:57 AM
 */
@Slf4j
public class CustomCommandListener implements CommandListener {

    @Override
    public void commandStarted(CommandStartedEvent event) {
        log.info("Mongo command: {} - {} - {}", event.getDatabaseName(), event.getCommand(), event.getConnectionDescription());
    }

    @Override
    public void commandSucceeded(CommandSucceededEvent event) {

    }

    @Override
    public void commandFailed(CommandFailedEvent event) {

    }

}
