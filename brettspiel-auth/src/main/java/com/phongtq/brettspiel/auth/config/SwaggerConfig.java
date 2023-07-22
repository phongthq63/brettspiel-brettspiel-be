package com.phongtq.brettspiel.auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Quách Thanh Phong
 * On 7/2/2023 - 2:55 AM
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Brettspiel Auth API")
                        .description("Brettspiel Auth API Mockup 2023-July" +
                                "</br> Các tham số header truyền lên trong mọi request</br>" +
                                "<ul><li><b>Authorization</b>: (<span style=\"color: red;\">Require</span>) JWT lấy được sau khi login gửi lên sẽ theo định dạng Bearer <TOKEN>. Để test được trên swagger mọi người sẽ TOKEN bằng cách click vào button Authoize</li>" +
                                "<li><b>Accept-Language</b>: Ngôn ngữ ứng dụng: default lấy theo ngôn ngữ máy</li>" +
                                "</ul>")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .components(new Components()
                        .addSecuritySchemes("JWT Token", new SecurityScheme()
                                .name("JWT")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("Basic Token", new SecurityScheme()
                                .name("Basic Auth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .in(SecurityScheme.In.HEADER)))
                .addSecurityItem(new SecurityRequirement()
                        .addList("JWT Token")
                        .addList("Basic Token"));
    }

}
