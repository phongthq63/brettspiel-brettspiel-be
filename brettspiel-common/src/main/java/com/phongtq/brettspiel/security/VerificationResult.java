package com.phongtq.brettspiel.security;

import lombok.Data;

import java.util.List;

/**
 * Created by Quach Thanh Phong
 * On 7/7/2023 - 11:15 AM
 */
@Data
public class VerificationResult {

    private boolean validated;

    private String jti;

    private String sub;

    private String name;

    private List<String> roles;

}
