package com.main.mongodb.mapper;

import com.main.domain.functionnal.model.Hero;
import com.main.mongodb.entity.HeroEntity;

import java.util.UUID;

public interface HeroEntityMapper {

    static Hero toDomain(HeroEntity entity){
        return Hero.builder()
                .id(entity.getId().toString())
                .name(entity.getName())
                .speciality(entity.getSpeciality())
                .armor(entity.getArmor())
                .Pv(entity.getPv())
                .lvl(entity.getLvl())
                .xp(entity.getXp())
                .rarity(entity.getRarity())
                .power(entity.getPower())
                .experiencePoint(entity.getExperiencePoint())
                .build();
    }

    static HeroEntity fromDomain(Hero domain){
        return HeroEntity.builder()
                .id(UUID.randomUUID())
                .armor(domain.getArmor())
                .experiencePoint(domain.getExperiencePoint())
                .name(domain.getName())
                .power(domain.getPower())
                .Pv(domain.getPv())
                .rarity(domain.getRarity())
                .speciality(domain.getSpeciality())
                .xp(domain.getXp())
                .build();
    }
}
