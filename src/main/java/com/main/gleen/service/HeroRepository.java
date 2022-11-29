package com.main.gleen.service;

import com.main.gleen.model.Hero;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

    @Query("{name:'?0'}")
    Hero findItemByName(String name);

    @Query("{_id:'?0'}")
    Hero findItembyID(String id);
    List<Hero> findAll();

}


