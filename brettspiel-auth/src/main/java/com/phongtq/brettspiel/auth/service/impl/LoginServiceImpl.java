package com.phongtq.brettspiel.auth.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.phongtq.brettspiel.auth.controller.request.LoginRequest;
import com.phongtq.brettspiel.auth.dto.UserTokenDTO;
import com.phongtq.brettspiel.auth.entity.User;
import com.phongtq.brettspiel.auth.repository.IUserRepository;
import com.phongtq.brettspiel.auth.service.ILoginService;
import com.phongtq.brettspiel.dto.R;
import com.phongtq.brettspiel.security.JwtProvider;
import com.phongtq.brettspiel.security.TokenInfo;
import com.phongtq.brettspiel.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 10:31 AM
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public Mono<R<UserTokenDTO>> login(LoginRequest loginRequest) {
        // Encryption password before save
        String encryptPassword = DigestUtil.md5Hex(loginRequest.getPassword());

//        return userRepository.findUserByUsernamePassword(loginRequest.getUsername(), encryptPassword)
//                .zipWhen(user -> {
//                    TokenInfo accessToken = jwtProvider.generateToken(user.getId(), user.getUsername(), new HashSet<>());
//                    String refreshToken = IdGenerator.nextUUID();
//
//                    return Mono.zip(Mono.just(accessToken), Mono.just(refreshToken));
//                })
//                .doOnNext(tuple -> {
//                    User user = tuple.getT1();
//                    String refreshToken = tuple.getT2().getT2();
//
//                    //Save refresh token
//                    // todo
//                })
//                .map(tuple -> {
//                    TokenInfo accessToken = tuple.getT2().getT1();
//                    String refreshToken = tuple.getT2().getT2();
//
//                    return R.ok(UserTokenDTO.builder()
//                            .tokenType("Bearer")
//                            .accessToken(accessToken.getToken())
//                            .refreshToken(refreshToken)
//                            .expiresIn(accessToken.getExpiration())
//                            .build());
//                })
//                .switchIfEmpty(Mono.just(R.failed("Account not exist. ")));
        return Mono.empty();
    }
}
