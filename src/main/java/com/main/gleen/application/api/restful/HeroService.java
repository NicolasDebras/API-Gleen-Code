package com.main.gleen.application.api.restful;

import com.main.gleen.domain.model.Hero;

import java.util.List;

public interface HeroService {
    void addHero(Hero hero);

    void removeHero(Hero hero);

    List<Hero> getHero();

    Hero getHeroById(String heroId);
}
