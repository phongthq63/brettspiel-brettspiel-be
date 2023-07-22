package com.phongtq.brettspiel.auth.controller.request;

import com.phongtq.brettspiel.dto.BaseRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 10:32 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RegisterRequest extends BaseRequest {

    @Schema(description = "Tên dăng nhập", example = "phongthq63@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Mật khẩu", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
