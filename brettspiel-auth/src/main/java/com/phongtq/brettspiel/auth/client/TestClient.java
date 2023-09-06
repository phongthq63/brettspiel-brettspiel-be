package com.phongtq.brettspiel.auth.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 9/6/2023 - 5:06 PM
 */
@ReactiveFeignClient(value = "test-client", url = "http://localhost:8080")
@Component
public interface TestClient {

    @GetMapping("/test")
    Mono<Object> test();

}
