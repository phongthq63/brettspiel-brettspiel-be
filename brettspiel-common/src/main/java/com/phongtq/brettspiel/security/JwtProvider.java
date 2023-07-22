package com.phongtq.brettspiel.security;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phongtq.brettspiel.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Quach Thanh Phong
 * On 7/7/2023 - 11:14 AM
 */
@Component
public class JwtProvider {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JWTSigner jwtSigner;

    private final String jwtSecret;

    private final String issuer;

    private final Integer expiration;


    public JwtProvider(@Value("${jwt.secret}") String jwtSecret,
                       @Value("${jwt.issuer:brettspiel}") String issuer,
                       @Value("${jwt.expiration:0}") Integer expiration) {
        this.jwtSecret = jwtSecret;
        this.issuer = issuer;
        this.expiration = expiration;
        this.jwtSigner = JWTSignerUtil.hs256(this.jwtSecret.getBytes(StandardCharsets.UTF_8));
        this.objectMapper.findAndRegisterModules();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public TokenInfo generateToken(String userId, String username, Set<String> roles) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        Date expiredAt = calendar.getTime();

        String token = JWT.create()
                .setSigner(this.jwtSigner)
                .setJWTId(IdGenerator.nextUUID())
                .setIssuer(this.issuer)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiresAt(expiredAt)
                .setSubject(String.valueOf(userId))
                .setPayload("roles", roles)
                .setPayload("name", username)
                .sign();
        return TokenInfo.builder()
                .token(token)
                .expiration(expiration)
                .build();
    }

    public VerificationResult verify(String token) {
        JWT jwt = JWT.of(token).setSigner(this.jwtSigner);
        boolean result = jwt.validate(0);
        VerificationResult verificationResult = new VerificationResult();
        verificationResult.setValidated(result);
        if (!result) {
            return verificationResult;
        }
        JWTPayload payload = jwt.getPayload();
        verificationResult.setJti((String) payload.getClaim("jti"));
        verificationResult.setSub((String) payload.getClaim("sub"));
        verificationResult.setName((String) payload.getClaim("name"));
        verificationResult.setRoles((List<String>) payload.getClaim("roles"));
        return verificationResult;
    }

}
