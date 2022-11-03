package com.main.gleen.application.adapter;

import com.main.gleen.application.api.restful.HeroService;
import com.main.gleen.domain.model.Hero;
import com.main.gleen.domain.spi.HeroPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceAdapter implements HeroService {

    private  HeroPersistencePort heroPersistencePort;


    @Autowired
    public HeroServiceAdapter(HeroPersistencePort heroPersistencePort) {
        this.heroPersistencePort = heroPersistencePort;
    }

    @Override
    public void addHero(Hero hero) {
        heroPersistencePort.addHero(hero);
    }

    @Override
    public void removeHero(Hero hero) {
        heroPersistencePort.removeHero(hero);
    }

    @Override
    public List<Hero> getHero() {
        return heroPersistencePort.getHero();
    }

    @Override
    public Hero getHeroById(String heroId) {
        return heroPersistencePort.getHeroById(heroId);
    }
}
