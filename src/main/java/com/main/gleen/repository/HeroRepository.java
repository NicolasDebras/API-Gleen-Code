package com.main.gleen.repository;

import com.main.gleen.model.Hero;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {

    @Query("{name:'?0'}")
    Hero findItemByName(String name);



    List<Hero> findAll();

    public long count();


}
