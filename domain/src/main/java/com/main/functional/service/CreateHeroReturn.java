package com.main.functional.service;

import com.main.functional.model.Hero;
import com.main.functional.model.HeroCreate;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;


@NoArgsConstructor
@Value
public class CreateHeroReturn {


    public static  Hero create(Hero hero, int health, int attack, int defense) {
        return Hero.builder()
                .name(hero.getName())
                .speciality(hero.getSpeciality())
                .rarity(hero.getRarity())
                .attack(attack)
                .health(health)
                .defense(defense)
                .build();
    }
}
