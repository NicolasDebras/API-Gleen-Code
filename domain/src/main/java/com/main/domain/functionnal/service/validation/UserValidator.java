package com.main.domain.functionnal.service.validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.User;
import io.vavr.control.Validation;

public interface UserValidator {

    static Validation<ApplicationError, User> validate(User user){
        return user.getPseudo().length() > 2 ?
                Valid(user) : Invalid(new ApplicationError("Invalid Power", null, user.getPseudo(), null));
    }
}
