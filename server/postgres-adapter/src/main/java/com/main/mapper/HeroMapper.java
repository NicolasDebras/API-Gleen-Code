package com.main.mapper;

import com.main.functional.model.Hero;
import com.main.entity.HeroEntity;

import java.util.List;

public interface HeroMapper {

    static Hero toDomain(HeroEntity entity) {
        return Hero.builder()
                .id(entity.getId())
                .rarity(RarityEntityMapper.toDomain(entity.getRarity()))
                .speciality(SpecialityEntityMapper.toDomain(entity.getSpeciality()))
                .name(entity.getName())
                .build();
    }


    static HeroEntity fromDomain(Hero domain) {
        return HeroEntity.builder()
                .id(domain.getId())
                .rarity(RarityEntityMapper.fromDomain(domain.getRarity()))
                .speciality(SpecialityEntityMapper.fromDomain(domain.getSpeciality()))
                .name(domain.getName())
                .build();
    }


    static List<Hero> toDomainList(List<HeroEntity> entities) {
        return entities.stream()
                .map(HeroMapper::toDomain)
                .toList();
    }
}
