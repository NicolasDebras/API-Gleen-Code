package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.HeroCreate;
import io.vavr.control.Validation;
import lombok.val;

public interface HeroValidator {

    static Validation<ApplicationError, HeroCreate> validate(HeroCreate hero) {
        val name = hero.getName();
        val speciality = hero.getSpeciality();
        val rarity = hero.getRarity();
        return name != null && name.length() > 3 && name.length() < 20
                && speciality != null && rarity != null
                ? Validation.valid(hero)
                : Validation.invalid(new ApplicationError("Invalid name", null, name, null));
    }
}
