package com.phongtq.brettspiel.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/7/2023 - 11:12 AM
 */
public interface IAuthorization {

    Mono<Boolean> isGranted(ServerHttpRequest request, Authentication authentication);

}
