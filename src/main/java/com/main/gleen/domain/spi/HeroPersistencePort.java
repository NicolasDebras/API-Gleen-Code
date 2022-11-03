package com.main.gleen.domain.spi;

import com.main.gleen.domain.model.Hero;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HeroPersistencePort {

    void addHero(Hero hero);
    void removeHero(Hero hero);
    List<Hero> getHero();
    Hero getHeroById(String heroId);

}
