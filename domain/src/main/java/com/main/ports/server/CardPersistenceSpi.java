package com.main.ports.server;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import io.vavr.control.Either;

import java.util.UUID;

public interface CardPersistenceSpi extends PersistenceSpi<Card, UUID> {

    Either<ApplicationError, Card> updateExperience(Card card);

    Either<ApplicationError, Card> updateLevel(Card card);

}
