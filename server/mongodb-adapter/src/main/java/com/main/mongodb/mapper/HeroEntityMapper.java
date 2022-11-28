package com.main.mongodb.mapper;

import com.main.domain.functionnal.model.Hero;
import com.main.mongodb.entity.HeroEntity;

public interface HeroEntityMapper {

    static Hero toDomain(HeroEntity entity) {
        return Hero.builder().id(entity.getId())
                .name(entity.getName())
                .level(entity.getLevel())
                .armor(entity.getArmor())
                .xp(entity.getXp())
                .pv(entity.getPv())
                .rarityHero(entity.getRarityHero())
                .power(entity.getPower())
                .experiencePoint(entity.getExperiencePoint())
                .specialityHero(entity.getSpecialityHero())
                .build();
    }


    static HeroEntity fromDomain(Hero hero){
        return HeroEntity.builder().id(hero.getId())
                .name(hero.getName())
                .level(hero.getLevel())
                .armor(hero.getArmor())
                .xp(hero.getXp())
                .pv(hero.getPv())
                .rarityHero(hero.getRarityHero())
                .power(hero.getPower())
                .experiencePoint(hero.getExperiencePoint())
                .specialityHero(hero.getSpecialityHero())
                .build();
    }
}
