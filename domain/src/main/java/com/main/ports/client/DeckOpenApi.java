package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.Deck;
import com.main.functional.model.DeckDTO;
import io.vavr.control.Either;

public interface DeckOpenApi {

    Either<ApplicationError, Deck> create(DeckDTO deck);
}
