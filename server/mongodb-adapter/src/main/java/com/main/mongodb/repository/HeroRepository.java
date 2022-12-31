package com.main.mongodb.repository;

import com.main.mongodb.entity.HeroEntity;
import io.vavr.control.Option;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@EnableMongoRepositories
@Repository
public interface HeroRepository extends MongoRepository<HeroEntity, Long> {

    Option<HeroEntity> findById(UUID id);
}
