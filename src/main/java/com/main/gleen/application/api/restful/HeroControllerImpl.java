package com.main.gleen.application.api.restful;

import com.main.gleen.application.adapter.HeroServiceAdapter;
import com.main.gleen.domain.model.Hero;
import com.main.gleen.infrastructure.adapter.mongodb.repository.HeroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class HeroControllerImpl  implements HeroController{

    private HeroServiceAdapter heroServiceAdapter;
    @Override
    public ResponseEntity<List<Hero>> getProducts() {
        try {
            List<Hero> herosFound =  heroServiceAdapter.getHero();
            return new ResponseEntity<>(herosFound, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Void> addProduct(Hero hero) {
        try{
            heroServiceAdapter.addHero(hero);
            return new ResponseEntity<>(  HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Void> removeProduct(Hero hero) {
       try{
           heroServiceAdapter.removeHero(hero);
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public ResponseEntity<Hero> getProductById(String heroId) {
        try{
            Hero createdHero = heroServiceAdapter.getHeroById(heroId);
            return new ResponseEntity<>(createdHero, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
