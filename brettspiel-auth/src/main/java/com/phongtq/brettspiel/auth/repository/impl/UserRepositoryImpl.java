package com.phongtq.brettspiel.auth.repository.impl;

import com.phongtq.brettspiel.auth.entity.User;
import com.phongtq.brettspiel.auth.repository.custom.ICustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Created by Quach Thanh Phong
 * On 7/5/2023 - 4:48 PM
 */
@Repository
public class UserRepositoryImpl implements ICustomUserRepository {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;


    @Override
    public Mono<User> findUserByUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return reactiveMongoTemplate.findOne(query, User.class);
    }

//    @Override
//    public Mono<User> findUserByUsernamePassword(String username, String password) {
//        Query query = Query.query(Criteria.where("username").is(username)
//                .and("password").is(password));
//        return reactiveMongoTemplate.findOne(query, User.class);
//    }
}
