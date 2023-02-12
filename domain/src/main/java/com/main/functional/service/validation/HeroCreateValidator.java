package com.main.functional.service.validation;

import com.main.functional.model.Hero;
import lombok.val;

import java.util.Optional;

public interface HeroCreateValidator {


    static Optional<Hero> validate(Hero hero)  {
        val name = hero.getName();
        val speciality = hero.getSpeciality();
        val rarity = hero.getRarity();
        return name != null && name.length() > 3 && name.length() < 20
                && speciality != null && rarity != null
                ? Optional.of(hero)
                :  Optional.empty();
    }
}
