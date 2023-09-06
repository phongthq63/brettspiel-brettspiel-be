package com.phongtq.brettspiel.auth.controller;

import com.phongtq.brettspiel.auth.client.TestClient;
import com.phongtq.brettspiel.auth.entity.User;
import com.phongtq.brettspiel.auth.repository.IUserRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Created by Quách Thanh Phong
 * On 7/2/2023 - 2:35 AM
 */
@Tag(name = "Test")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private TestClient testClient;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;


    @GetMapping("")
    public Mono<Object> test() {
        return Mono.just("ssssss");
    }

    @GetMapping("/test")
    public Mono<Object> testtest() {
        return testClient.test();
    }

    @GetMapping("/token")
    public Object token() {
        return testClient.test();
    }

}
