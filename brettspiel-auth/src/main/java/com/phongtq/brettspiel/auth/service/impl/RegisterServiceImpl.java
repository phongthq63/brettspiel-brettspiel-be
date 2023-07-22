package com.phongtq.brettspiel.auth.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.phongtq.brettspiel.auth.controller.request.RegisterRequest;
import com.phongtq.brettspiel.auth.entity.User;
import com.phongtq.brettspiel.auth.repository.IUserRepository;
import com.phongtq.brettspiel.auth.service.IRegisterService;
import com.phongtq.brettspiel.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 11:07 AM
 */
@Service
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Mono<R<Object>> register(RegisterRequest registerRequest) {
        return userRepository.findUserByUsername(registerRequest.getUsername())
                .map(user -> R.failed("Username have been registed. "))
                .switchIfEmpty(Mono.fromCallable(() -> {
                            // Encryption password before save
                            String encryptPassword = DigestUtil.md5Hex(registerRequest.getPassword());

                            return User.builder()
                                    .username(registerRequest.getUsername())
                                    .password(encryptPassword)
                                    .build();
                        })
                        .flatMap(user ->  userRepository.insert(user))
                        .map(user -> R.ok())
                        .switchIfEmpty(Mono.just(R.failed("System busy. "))));
    }
}
