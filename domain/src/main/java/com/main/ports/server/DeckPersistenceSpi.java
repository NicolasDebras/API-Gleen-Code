package com.main.ports.server;

import com.main.ApplicationError;
import com.main.functional.model.Deck;
import io.vavr.control.Either;

import java.util.UUID;

public interface DeckPersistenceSpi extends PersistenceSpi<Deck, UUID> {

    Either<ApplicationError, Deck> save(UUID idUser, Deck deck);

    Either<ApplicationError, Deck> find(UUID idUser);
}
