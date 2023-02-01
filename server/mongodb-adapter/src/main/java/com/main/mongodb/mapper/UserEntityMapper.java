package com.main.mongodb.mapper;

import com.main.domain.functionnal.model.User;
import com.main.mongodb.entity.UserEntity;

import java.util.UUID;

public interface UserEntityMapper {

    static User toDomain(UserEntity entity){
        return User.builder()
                .id(entity.getId().toString())
                .pseudo(entity.getUsername())
                .jeton(entity.getJeton())
                .build();
    }

    static UserEntity fromDomain(User domain){
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .username(domain.getPseudo())
                .jeton(domain.getJeton())
                .build();
    }
}
