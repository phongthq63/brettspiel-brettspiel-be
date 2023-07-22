package com.phongtq.brettspiel.auth.service;

import com.phongtq.brettspiel.auth.controller.request.LoginRequest;
import com.phongtq.brettspiel.auth.dto.UserTokenDTO;
import com.phongtq.brettspiel.dto.R;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 10:30 AM
 */
public interface ILoginService {

    Mono<R<UserTokenDTO>> login(LoginRequest loginRequest);

}
