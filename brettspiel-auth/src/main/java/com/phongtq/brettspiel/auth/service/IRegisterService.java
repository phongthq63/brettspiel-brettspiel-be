package com.phongtq.brettspiel.auth.service;

import com.phongtq.brettspiel.auth.controller.request.RegisterRequest;
import com.phongtq.brettspiel.dto.R;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 11:07 AM
 */
public interface IRegisterService {

    Mono<R<Object>> register(RegisterRequest registerRequest);

}
