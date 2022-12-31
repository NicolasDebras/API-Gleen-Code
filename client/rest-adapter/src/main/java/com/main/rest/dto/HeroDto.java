package com.main.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.main.domain.functionnal.model.RarityHero;
import com.main.domain.functionnal.model.SpecialityHero;
import lombok.Builder;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto(

        UUID id ,
       String name,
       int pv,
       int xp,
       int power,
       int armor ,
       int level,
      int experiencePoint ,
      SpecialityHero specialityHero,
     RarityHero rarityHero
) {
}
