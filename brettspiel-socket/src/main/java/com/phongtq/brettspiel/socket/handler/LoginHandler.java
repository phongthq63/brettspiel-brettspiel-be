package com.phongtq.brettspiel.socket.handler;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * Created by Quách Thanh Phong
 * On 7/3/2023 - 12:46 AM
 */
@Slf4j
@Controller
public class LoginHandler implements AuthorizationListener {

    @Value("${socketio.authentication:#{false}}")
    private Boolean authentication;

    @Override
    public boolean isAuthorized(HandshakeData handshakeData) {
        return true;
    }
}
