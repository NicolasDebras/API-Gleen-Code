package com.main.mapper;

import com.main.functional.model.AdvantageOtherHero;
import com.main.entity.AdvantageOtherHeroEntity;

public interface AdvantageOtherHeroMapper {

    static AdvantageOtherHero toDomain(AdvantageOtherHeroEntity entity) {
        return AdvantageOtherHero.builder()
                .id(entity.getId())
                .name(entity.getName())
                .power(entity.getPower())
                .build();
    }

    static AdvantageOtherHeroEntity fromDomain(AdvantageOtherHero domain) {
        return AdvantageOtherHeroEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .power(domain.getPower())
                .build();
    }
}
