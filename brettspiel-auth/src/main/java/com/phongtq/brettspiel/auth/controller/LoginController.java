package com.phongtq.brettspiel.auth.controller;

import com.phongtq.brettspiel.auth.controller.request.LoginRequest;
import com.phongtq.brettspiel.auth.dto.UserTokenDTO;
import com.phongtq.brettspiel.auth.service.ILoginService;
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
 * On 7/5/2023 - 10:18 AM
 */
@Tag(name = "Login")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;


    @Operation(summary = "Đăng nhập bằng tên đăng nhập/mật khẩu")
    @PostMapping("")
    public Mono<R<UserTokenDTO>> login(@RequestBody LoginRequest body) {
        return loginService.login(body);
    }

}
