package com.phongtq.brettspiel.auth.repository.custom;

import com.phongtq.brettspiel.auth.entity.User;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 10:44 AM
 */
public interface ICustomUserRepository {

    Mono<User> findUserByUsername(String username);

    Mono<User> findUserByUsernamePassword(String username, String password);

}
