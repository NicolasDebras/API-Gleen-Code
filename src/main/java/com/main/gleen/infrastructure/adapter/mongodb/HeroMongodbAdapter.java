package com.main.gleen.infrastructure.adapter.mongodb;

import com.main.gleen.domain.model.Hero;
import com.main.gleen.domain.spi.HeroPersistencePort;

import java.util.List;

public interface HeroMongodbAdapter extends HeroPersistencePort {
    @Override
    public void addHero(Hero hero);

    @Override
    public void removeHero(Hero hero) ;

    @Override
    List<Hero> getHero() ;

    @Override
    public Hero getHeroById(String heroId);
}
