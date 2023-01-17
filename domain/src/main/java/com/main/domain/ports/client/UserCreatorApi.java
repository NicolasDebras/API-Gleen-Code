package com.main.domain.ports.client;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.User;
import io.vavr.control.Either;

public interface UserCreatorApi {

    Either<ApplicationError, User> create(User user);
}
