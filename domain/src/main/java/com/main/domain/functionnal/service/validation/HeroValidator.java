package com.main.domain.functionnal.service.validation;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.Hero;
import lombok.val;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;
import io.vavr.control.Validation;

public interface HeroValidator {

    static Validation<ApplicationError, Hero> validate(Hero hero){
        /*val name = hero.getName();
        val pv = hero.getPv();
        val level = hero.getLvl();
        val xp = hero.getXp();
        val armor = hero.getArmor();
        val rarity = hero.getRarity();
        val speciality = hero.getSpeciality();*/
        val power = hero.getPower();
        return power >0
                ? Valid(hero)
                : Invalid(new ApplicationError("Invalid Power", null, power, null));
    }
}
