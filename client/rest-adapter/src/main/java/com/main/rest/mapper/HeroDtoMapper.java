package com.main.rest.mapper;

import com.main.domain.functionnal.model.Hero;
import com.main.rest.dto.HeroDto;

public interface HeroDtoMapper {
    static HeroDto toDto(Hero hero){
        return new HeroDto(
                hero.getId(),
                hero.getName(),
                hero.getPv(),
                hero.getXp(),
                hero.getPower(),
                hero.getArmor(),
                hero.getLevel(),
                hero.getExperiencePoint(),
                hero.getSpecialityHero(),
                hero.getRarityHero()
        );
    }


    static Hero heroCreationToDomain(HeroDto heroDto){
        return Hero.builder().id(heroDto.id()).build();
    }
}
