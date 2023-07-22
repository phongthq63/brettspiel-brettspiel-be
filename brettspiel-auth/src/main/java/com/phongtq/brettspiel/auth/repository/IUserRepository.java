package com.phongtq.brettspiel.auth.repository;

import com.phongtq.brettspiel.auth.entity.User;
import com.phongtq.brettspiel.auth.repository.custom.ICustomUserRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 4:27 PM
 */
public interface IUserRepository extends ReactiveMongoRepository<User, String>, ICustomUserRepository {
}
