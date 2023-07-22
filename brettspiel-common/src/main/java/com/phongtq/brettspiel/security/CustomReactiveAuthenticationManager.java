package com.phongtq.brettspiel.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 3:00 PM
 */
@Component
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtProvider jwtProvider;


    public CustomReactiveAuthenticationManager(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        VerificationResult verificationResult = jwtProvider.verify(token);
        if (!verificationResult.isValidated()) {
            return Mono.error(new UnauthorizedException("Invalid token"));
//            return Mono.empty();
        }

        String userId = verificationResult.getSub();
        List<SimpleGrantedAuthority> authorities = verificationResult.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserPrincipal principal = new UserPrincipal(userId, verificationResult.getName());
        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(principal, verificationResult, authorities));
    }

}
