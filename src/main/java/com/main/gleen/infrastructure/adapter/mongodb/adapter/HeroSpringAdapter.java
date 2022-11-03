package com.main.gleen.infrastructure.adapter.mongodb.adapter;


import com.main.gleen.domain.model.Hero;
import com.main.gleen.domain.spi.HeroPersistencePort;
import com.main.gleen.infrastructure.adapter.mongodb.entity.HeroEntity;
import com.main.gleen.infrastructure.adapter.mongodb.repository.HeroRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class HeroSpringAdapter implements HeroPersistencePort {

    private HeroRepository heroRepository;

    public HeroSpringAdapter(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }
    @Override
    public void addHero(Hero hero) {
        HeroEntity heroEntity = HeroEntity.builder().build();
        BeanUtils.copyProperties(hero, heroEntity);
        heroRepository.save(heroEntity);

    }

    @Override
    public void removeHero(Hero hero) {
        HeroEntity heroEntity = HeroEntity.builder().build();
        BeanUtils.copyProperties(hero, heroEntity);
        heroRepository.delete(heroEntity);
    }

    @Override
    public List<Hero> getHero() {
        List<Hero> heroList = new ArrayList<Hero>();
        List<HeroEntity> heroEntityList = heroRepository.findAll();
        for(HeroEntity heroEntity: heroEntityList){
            Hero hero = Hero.builder().build();
            BeanUtils.copyProperties(heroEntity, hero);
            heroList.add(hero);
        }
        return heroList;

    }

    @Override
    public Hero getHeroById(String heroId) {
        HeroEntity heroEntity = heroRepository.findHeroById(heroId);
        Hero hero = Hero.builder().build();
        BeanUtils.copyProperties(heroEntity, hero);
        return hero;
    }
}
