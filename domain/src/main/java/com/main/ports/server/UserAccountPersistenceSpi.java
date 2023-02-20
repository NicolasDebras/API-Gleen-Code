package com.main.ports.server;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Either;

import java.util.UUID;

public interface UserAccountPersistenceSpi extends PersistenceSpi<User, UUID> {

    Either<ApplicationError, User> updateToken(UUID idCard);

}
