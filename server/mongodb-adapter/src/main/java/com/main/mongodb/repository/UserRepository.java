package com.main.mongodb.repository;

import com.main.mongodb.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends MongoRepository<UserEntity, UUID> {
}
