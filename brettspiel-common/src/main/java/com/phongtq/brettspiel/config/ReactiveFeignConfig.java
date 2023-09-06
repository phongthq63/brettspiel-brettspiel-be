package com.phongtq.brettspiel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.ReactiveOptions;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.client.ReactiveHttpRequestInterceptors;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactivefeign.webclient.WebReactiveOptions;

/**
 * Created by Quach Thanh Phong
 * On 9/6/2023 - 5:04 PM
 */
@EnableReactiveFeignClients(basePackages = "com.phongtq.brettspiel", basePackageClasses = ReactiveFeignConfig.class)
@Configuration
public class ReactiveFeignConfig {

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(3000)
                .setWriteTimeoutMillis(3000)
                .setResponseTimeoutMillis(3000)
                .build();
    }

    private String apiKey = "test";

    private static final String API_KEY_HEADER = "X-API-KEY";

    @Bean
    public ReactiveHttpRequestInterceptor apiKeyInterceptor() {
        return ReactiveHttpRequestInterceptors.addHeader(API_KEY_HEADER, apiKey);
    }

}
