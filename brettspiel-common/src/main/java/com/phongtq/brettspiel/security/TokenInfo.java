package com.phongtq.brettspiel.security;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 1:29 PM
 */
@Data
@Builder
public class TokenInfo {

    private String token;

    private Integer expiration;

}
