package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Validation;
import lombok.val;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;
public interface UserCreatedValidator {


    static Validation<ApplicationError, User> validate(User user){
        val username = user.getUsername();
        return username != null && username.length() < 15 && username.length() > 3 && username.matches("[a-zA-Z0-9_]+")
                ? Valid(user)
                : Invalid(new ApplicationError("Invalid username length ", null, username, null));
    }
}
