package com.main.domain.functionnal.service;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.User;
import com.main.domain.functionnal.service.validation.UserValidator;
import com.main.domain.ports.client.UserCreatorApi;
import com.main.domain.ports.server.UserPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {


    private final UserPersistenceSpi spi;
    @Override
    public Either<ApplicationError, User> create(User user) {
        return UserValidator.validate(user)
                .toEither()
                .peekLeft(
                        error -> log.error("An error occurred while validating hero : {}", error)
                )
                .flatMap(spi::save);
    }
}
