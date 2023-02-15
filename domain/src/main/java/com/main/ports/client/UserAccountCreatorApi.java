package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Either;

public interface UserAccountCreatorApi {

    Either<ApplicationError, User> create(User user);
}
