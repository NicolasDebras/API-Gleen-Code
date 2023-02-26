package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.User;
import com.main.functional.service.validation.UserCreatedValidator;
import com.main.ports.client.UserAccountCreatorApi;
import com.main.ports.server.UserAccountPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class UserAccountCreatorService implements UserAccountCreatorApi {

    private final UserAccountPersistenceSpi spi;
    @Override
    public Either<ApplicationError, User> create(User user) {
        return UserCreatedValidator.validate(user)
                .toEither()
                .peekLeft(error -> log.error("An error occurred while validating user : {}", error))
                .flatMap(hero -> spi.findByUsername(hero.getUsername())
                        .isEmpty() ? spi.save(hero) : Either.left(new ApplicationError("User already exists", null, hero, null))
                );

    }
}
