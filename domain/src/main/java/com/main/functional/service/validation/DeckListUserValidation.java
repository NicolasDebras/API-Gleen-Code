package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Validation;

import java.util.UUID;

public interface DeckListUserValidation {

    static Validation<ApplicationError, UUID> validate(UUID idUser){
        return idUser != null
                ? Validation.valid(idUser)
                : Validation.invalid(new ApplicationError("Invalid idUser", null, null, null));
    }
}
