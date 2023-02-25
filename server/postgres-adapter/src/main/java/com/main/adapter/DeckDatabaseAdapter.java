package com.main.adapter;

import com.main.ApplicationError;
import com.main.mapper.CardEntityMapper;
import com.main.functional.model.Deck;
import com.main.ports.server.DeckPersistenceSpi;
import com.main.repository.CardRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeckDatabaseAdapter implements DeckPersistenceSpi {

    private final CardRepository repository;

    @Override
    public Either<ApplicationError, Deck> save(UUID idUser, Deck deck) {
        return null;
    }

    @Override
    public Either<ApplicationError, Deck> find(UUID idUser) {
        val deck = repository.findAllByUser(idUser);
        if (deck.isEmpty()) {
            return Either.left(new ApplicationError("Error while finding deck by id user", null, idUser, null));
        }
        return Either.right(CardEntityMapper.toDomainDeck(deck.get()));
    }

    @Override
    public Either<ApplicationError, Deck> save(Deck o) {
        return null;
    }

    @Override
    public Option<Deck> findById(UUID uuid) {
        val deck = repository.findAllByUser(uuid);
        if (deck.isEmpty()) {
            return Option.none();
        }
        return Option.of(CardEntityMapper.toDomainDeck(deck.get()));
    }
}
