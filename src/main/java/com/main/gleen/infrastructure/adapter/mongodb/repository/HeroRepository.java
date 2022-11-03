package com.main.gleen.infrastructure.adapter.mongodb.repository;

import com.main.gleen.domain.model.Hero;
import com.main.gleen.infrastructure.adapter.mongodb.entity.HeroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends MongoRepository<HeroEntity, String> {

    @Query("{name:'?0'}")
    HeroEntity findItemByName(String name);

    HeroEntity findHeroById(String id);


    List<HeroEntity> findAll();

    public long count();


}
