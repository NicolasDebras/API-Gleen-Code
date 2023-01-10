package com.main.rest.mapper;

import com.main.domain.functionnal.model.Hero;
import com.main.domain.functionnal.model.Rarity;
import com.main.domain.functionnal.model.Speciality;
import com.main.rest.dto.HeroCreationDto;

public interface HeroDtoMapper {

    static HeroCreationDto toDo(Hero hero){
        return new HeroCreationDto(
                hero.getName(),
                hero.getPv(),
                hero.getSpeciality().name(),
                hero.getRarity().name(),
                hero.getXp(),
                hero.getPower(),
                hero.getArmor(),
                hero.getLvl(),
                hero.getExperiencePoint()
        );
    }

    static Hero heroCreationToDomain(HeroCreationDto dto){
        return  Hero.builder()
                .armor(dto.armor())
                .Pv(dto.pv())
                .xp(dto.pv())
                .lvl(dto.level())
                .rarity(Rarity.valueOf(dto.rarity()))
                .power(dto.power())
                .speciality(Speciality.valueOf(dto.speciality()))
                .name(dto.name())
                .build();
    }
}
