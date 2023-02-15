package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import io.vavr.control.Validation;

public interface HeroCreatedValidation {

    static Validation<ApplicationError, Hero> validate(Hero hero){
        return hero.getSpeciality() != null && hero.getName() !=null  && hero.getName().length() < 15 && hero.getName().matches("[a-zA-Z_]+")
                && hero.getName().length() > 3
                && hero.getRarity() != null
                ? Validation.valid(hero)
                : Validation.invalid(new ApplicationError("Invalid rarity percentage", null, hero.getRarity().getPercentage(), null));
    }
}
