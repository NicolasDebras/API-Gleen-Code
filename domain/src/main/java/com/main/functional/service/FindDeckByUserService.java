package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Deck;
import com.main.functional.model.User;
import com.main.functional.service.validation.DeckListUserValidation;
import com.main.ports.client.FindDeckUserApi;
import com.main.ports.server.DeckPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FindDeckByUserService implements FindDeckUserApi {

    private final UserAccountPersistenceSpi userAccountPersistenceSpi;

    private final DeckPersistenceSpi deckPersistenceSpi;
    @Override
    public Either<ApplicationError, Deck> find(UUID idUser) {
        return DeckListUserValidation.validate(idUser)
                .toEither(() -> new ApplicationError("Invalid idUser", null, idUser, null))
                .flatMap(this::findUser)
                .flatMap(user -> deckPersistenceSpi.find(idUser)
                        .toEither(() -> new ApplicationError("Not possible to find deck", null, idUser, null)));
    }

    private Either<ApplicationError, User> findUser(UUID idUser) {
        return userAccountPersistenceSpi.findById(idUser)
                .toEither(() -> new ApplicationError("Not possible to find user", null, idUser, null));
    }
}
