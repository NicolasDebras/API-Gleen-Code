package com.main.gleen.Controller;

import com.main.gleen.model.Hero;
import com.main.gleen.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
public class HeroController {


    @Autowired
    private HeroService heroService;

    @GetMapping("/hero")
    public ResponseEntity<Iterable<Hero>> getHeros(){
        try {
            Iterable<Hero> herosFound =  heroService.getHeros();
            return new ResponseEntity<>(herosFound, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/hero/{id}")
    public ResponseEntity<Hero> getHero(@PathVariable("id")String id){
        try {
            Optional<Hero> heroFound = heroService.getHero(id);
            return heroFound.map(hero -> new ResponseEntity<>(hero, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
