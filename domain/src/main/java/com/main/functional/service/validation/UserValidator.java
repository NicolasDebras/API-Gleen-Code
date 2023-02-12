package com.main.functional.service.validation;

import com.main.functional.model.User;
import lombok.val;

import java.util.Optional;

public interface UserValidator {

    static Optional<User> validate(User user) {
        val username = user.getUsername();
        return username != null && username.length() > 3 && username.length() < 20
                ? Optional.of(user)
                : Optional.empty();
    }
}
