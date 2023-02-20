package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.Fighter;
import io.vavr.control.Validation;

public interface FighterValidation {

    static Validation<ApplicationError, Fighter> validate(Fighter fighter) {
        return fighter != null && fighter.getAttackCard() != null && fighter.getDefenseCard() != null
                && fighter.getAttackCard().getId() != null && fighter.getDefenseCard().getId() != null
                ? Validation.valid(fighter)
                :  Validation.invalid(new ApplicationError("Fighter is null", null, fighter, null));
    }
}
