package com.phongtq.brettspiel.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * Created by Quach Thanh Phong
 * On 7/10/2023 - 9:51 AM
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class BaseEntity {

    @MongoId(targetType = FieldType.STRING)
    private String id;

}
