package com.main.gleen.Controller;

import com.main.gleen.model.Hero;
import com.main.gleen.service.HeroService;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController()
public class HeroController {


    @Autowired
    private HeroService heroService;

    @GetMapping("/hero")
    public Iterable<Hero> getHeros(){
        return heroService.getHeros();
    }

    @GetMapping("/hero/{id}")
    public Optional<Hero> getHero(@PathVariable("id")String id){
        return heroService.getHero(id);

    }

    @PostMapping("/hero")
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        try {
            Hero heroCreate = heroService.newHero(hero);
            return new ResponseEntity<>(heroCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hero/{id}")
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id")String id){
        try {
            heroService.deleteHeroById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hero/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id")String id,  @RequestBody Hero hero){
        Optional<Hero> heroData = heroService.getHero(id);
        if(heroData.isPresent()){
            Hero updateHero = heroData.get();
            updateHero.setName(hero.getName());
            updateHero.setPv(hero.getPv());
            return new ResponseEntity<Hero>(heroService.newHero(updateHero), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
