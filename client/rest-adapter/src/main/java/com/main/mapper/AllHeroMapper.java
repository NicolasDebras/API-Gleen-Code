package com.main.mapper;

import com.main.functional.model.Hero;
import com.main.dto.AllHeroDto;
import com.main.dto.HeroDto;

import java.util.List;

public interface AllHeroMapper {

    static AllHeroDto toDto(List<Hero> heroes) {
        List<HeroDto> heroDto = heroes.stream()
                .map(com.main.dto.mapper.HeroMapper::toDto)
                .toList();
        return new AllHeroDto(heroDto);

    }


}
