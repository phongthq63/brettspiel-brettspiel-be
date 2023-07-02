package com.phongtq.brettspiel.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by Quách Thanh Phong
 * On 7/2/2023 - 2:35 AM
 */
@RestController("/test")
public class TestController {

    @GetMapping("")
    public Mono<Object> test() {
        return Mono.just("Ok");
    }

}
