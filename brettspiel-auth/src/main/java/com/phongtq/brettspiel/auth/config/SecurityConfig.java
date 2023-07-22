package com.phongtq.brettspiel.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 2:08 PM
 */
@Configuration
public class SecurityConfig {

//    @Autowired
//    private ReactiveAuthenticationManager reactiveAuthenticationManager;

    @Autowired
    private ServerSecurityContextRepository serverSecurityContextRepository;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .exceptionHandling(exceptionHandlingSpec -> {
                    exceptionHandlingSpec.authenticationEntryPoint((exchange, ex) -> Mono.fromRunnable(() -> {
                        exchange.getResponse().getHeaders().setAccessControlAllowOrigin(CorsConfiguration.ALL);
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    }));
                    exceptionHandlingSpec.accessDeniedHandler((exchange, denied) -> Mono.fromRunnable(() -> {
                        exchange.getResponse().getHeaders().setAccessControlAllowOrigin(CorsConfiguration.ALL);
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    }));
                })
//                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(serverSecurityContextRepository)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/v3/api-docs/**"
                                , "/swagger-resources/configuration/ui"
                                , "/swagger-resources"
                                , "/swagger-resources/configuration/security"
                                , "/swagger-ui.html"
                                , "/webjars/**").permitAll()
                        .pathMatchers("/test",
                                "/test/**").permitAll()
                        .anyExchange().authenticated())
                .build();
    }

}
