package com.main.mongodb.mapper;

import com.main.domain.functionnal.model.User;
import com.main.mongodb.entity.UserEntity;

import java.util.UUID;

public interface UserEntityMapper {

    static User toDomain(UserEntity entity){
        return User.builder()
                .pseudo(entity.getUsername())
                .build();
    }

    static UserEntity fromDomain(User domain){
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(domain.getPseudo())
                .build();
    }
}
