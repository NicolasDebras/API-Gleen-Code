package com.main.ports.server;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.UUID;

public interface UserAccountPersistenceSpi extends PersistenceSpi<User, UUID> {

    Either<ApplicationError, User> updateToken(User user);

    Option<User> findByUsername(String username);

}
