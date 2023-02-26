package com.main.mapper;

import com.main.functional.model.AdvantageOtherHero;
import com.main.functional.model.Speciality;
import com.main.entity.AdvantageOtherHeroEntity;
import com.main.entity.SpecialityEntity;

import java.util.List;
import java.util.UUID;

public interface SpecialityEntityMapper {

    static Speciality toDomain(SpecialityEntity entity) {
        return Speciality.builder()
                .id(entity.getId())
                .name(entity.getName())
                .armor(entity.getArmor())
                .power(entity.getPower())
                .advantageOtherHeroes(List.of(entity.getAdvantageOtherHero().stream().map(AdvantageOtherHeroMapper::toDomain).toArray(AdvantageOtherHero[]::new)))
                .health(entity.getHealth())
                .build();
    }

    static SpecialityEntity fromDomain(Speciality domain) {
        return SpecialityEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .armor(domain.getArmor())
                .power(domain.getPower())
                .health(domain.getHealth())
                .advantageOtherHero(List.of(domain.getAdvantageOtherHeroes().stream().map(AdvantageOtherHeroMapper::fromDomain).toArray(AdvantageOtherHeroEntity[]::new)))
                .build();
    }
}
