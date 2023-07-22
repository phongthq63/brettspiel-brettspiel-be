package com.phongtq.brettspiel.socket.service;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;

/**
 * Created by Quach Thanh Phong
 * On 7/3/2023 - 10:32 AM
 */
public interface ISocketIOService {

    void start();

    void stop();

    boolean isCluster();

}
