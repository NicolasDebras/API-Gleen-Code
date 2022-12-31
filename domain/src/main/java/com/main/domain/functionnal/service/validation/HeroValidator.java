package com.main.domain.functionnal.service.validation;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.Hero;
import lombok.val;
import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;
import io.vavr.control.Validation;

public interface HeroValidator {

    static Validation<ApplicationError, Hero> validate(Hero hero){
        val ssName = hero.getName();
        return ssName != null && ssName.length() >= 4 && ssName.matches("[A-Z][a-z]+")
                ? Valid(hero)
                : Invalid(new ApplicationError("Invalid Name", null, ssName, null));
    }
}
