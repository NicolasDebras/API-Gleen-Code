package com.main.gleen.repository;

import com.main.gleen.model.Hero;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@SpringBootApplication
public interface ItemRepository extends MongoRepository<Hero, String> {

    @Query("{name:'?0'}")
    Hero findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Hero> findAll(String category);

    public long count();
}
