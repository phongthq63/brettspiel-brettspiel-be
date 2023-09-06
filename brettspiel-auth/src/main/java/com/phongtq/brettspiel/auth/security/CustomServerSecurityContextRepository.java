package com.phongtq.brettspiel.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 3:05 PM
 */
@Slf4j
@Component
public class CustomServerSecurityContextRepository implements ServerSecurityContextRepository {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;


    public CustomServerSecurityContextRepository(ReactiveAuthenticationManager reactiveAuthenticationManager) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
//                .doOnNext(authHeader -> log.info("HEADER - Authorization: {}", authHeader))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .flatMap(authHeader -> {
                    String authToken = authHeader.substring(7);
                    Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(authToken, authToken);
                    return reactiveAuthenticationManager.authenticate(authentication);
                })
                .map(SecurityContextImpl::new);
    }

}
