package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.HeroFind;
import io.vavr.control.Validation;

public interface HeroFindValidator {

    static Validation<ApplicationError, HeroFind> validate(HeroFind heroFind) {
        return heroFind != null  && heroFind.getLimit() > 0 && heroFind.getLimit() < 50 && heroFind.getOffset() >= 0
                ? Validation.valid(heroFind)
                : Validation.invalid(new ApplicationError("Invalid heroFind", null, heroFind, null));

    }
}
