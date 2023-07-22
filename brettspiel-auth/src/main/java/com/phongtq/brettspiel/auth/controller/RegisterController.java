package com.phongtq.brettspiel.auth.controller;

import com.phongtq.brettspiel.auth.controller.request.RegisterRequest;
import com.phongtq.brettspiel.auth.service.IRegisterService;
import com.phongtq.brettspiel.dto.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 10:56 AM
 */
@Tag(name = "Register")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IRegisterService registerService;


    @Operation(summary = "Đăng kí tài khoản bằng tên đăng nhập/mật khẩu")
    @PostMapping("")
    public Mono<R<Object>> register(@RequestBody RegisterRequest body) {
        return registerService.register(body);
    }

}
