package com.phongtq.brettspiel.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

/**
 * Created by Quách Thanh Phong
 * On 7/2/2023 - 2:24 AM
 */
@SpringBootApplication(scanBasePackages = "com.phongtq.brettspiel", exclude = {
        MongoReactiveAutoConfiguration.class,
        MongoReactiveDataAutoConfiguration.class
})
public class BrettspielAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrettspielAuthApplication.class, args);
    }

}
