package com.main.functional.service;

import com.main.functional.model.Hero;
import com.main.functional.model.HeroCreate;

import java.util.UUID;

public class CreateHeroReturn {


    private Hero create(HeroCreate heroCreate, int health, int attack, int defense) {
        return Hero.builder()
                .id(UUID.randomUUID())
                .name(heroCreate.getName())
                .speciality(heroCreate.getSpeciality())
                .rarity(heroCreate.getRarity())
                .attack(attack)
                .health(health)
                .defense(defense)
                .build();
    }
}
