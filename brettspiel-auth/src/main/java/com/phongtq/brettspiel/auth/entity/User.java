package com.phongtq.brettspiel.auth.entity;

import com.phongtq.brettspiel.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 9:50 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document("user")
public class User extends BaseEntity {

    private String username;

    private String password;

    private String email;

}
