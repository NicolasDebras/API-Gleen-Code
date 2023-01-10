package com.main.mongodb.repository;

import com.main.mongodb.entity.HeroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroRepository extends MongoRepository<HeroEntity, UUID> {
}
