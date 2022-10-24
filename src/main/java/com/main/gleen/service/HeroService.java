package com.main.gleen.service;

import com.main.gleen.model.Hero;
import com.main.gleen.repository.HeroRepository;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    public Iterable<Hero> getHeros(){
        return heroRepository.findAll();
    }

    public Optional<Hero> getHero(String id){
        return heroRepository.findById(id);
    }

    public Hero newHero(Hero hero){
        return heroRepository.save(hero);
    }

    public void deleteHeroById(String id){
         heroRepository.deleteById(id);
    }

}
