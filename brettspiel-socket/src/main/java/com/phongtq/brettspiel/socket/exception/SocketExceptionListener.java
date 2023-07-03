package com.phongtq.brettspiel.socket.exception;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Quách Thanh Phong
 * On 7/3/2023 - 12:36 AM
 */
@Slf4j
@Component
public class SocketExceptionListener implements ExceptionListener {

    @Override
    public void onEventException(Exception e, List<Object> list, SocketIOClient socketIOClient) {
        log.error("socket-io --- event exception, {} {}", socketIOClient, list, e);
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient socketIOClient) {
        log.error("socket-io --- disconnect exception, {}", socketIOClient, e);
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient socketIOClient) {
        log.error("socket-io --- connect exception, {}", socketIOClient, e);
    }

    @Override
    public void onPingException(Exception e, SocketIOClient socketIOClient) {
        log.error("socket-io --- ping exception, {}", socketIOClient, e);
    }

    @Override
    public void onPongException(Exception e, SocketIOClient socketIOClient) {
        log.error("socket-io --- pong exception, {}", socketIOClient, e);
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        log.error("socket-io --- connection exception, {}", channelHandlerContext.channel(), throwable);
        return false;
    }
}
