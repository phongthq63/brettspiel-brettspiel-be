package com.phongtq.brettspiel.security;

import java.security.Principal;

/**
 * Created by Quach Thanh Phong
 * On 7/7/2023 - 4:01 PM
 */
public class UserPrincipal implements Principal {

    private final String id;

    private final String name;

    public UserPrincipal(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}
