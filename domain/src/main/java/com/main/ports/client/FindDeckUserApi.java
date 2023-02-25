package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.Deck;
import io.vavr.control.Either;

import java.util.UUID;

public interface FindDeckUserApi {

        Either<ApplicationError, Deck> find(UUID idUser);
}
