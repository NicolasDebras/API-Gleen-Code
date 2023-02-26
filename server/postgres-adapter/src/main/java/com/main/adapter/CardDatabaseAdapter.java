package com.main.adapter;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.ports.server.CardPersistenceSpi;
import com.main.entity.CardEntity;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import com.main.mapper.CardEntityMapper;
import org.springframework.stereotype.Service;
import com.main.repository.CardRepository;
import com.main.repository.LevelRepository;
import org.springframework.transaction.annotation.Transactional;
import static io.vavr.API.Try;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardDatabaseAdapter implements CardPersistenceSpi {

    private final CardRepository cardRepository;

    private final LevelRepository levelRepository;
    @Override
    @Transactional
    public Either<ApplicationError, Card> updateExperience(Card card) {
        val cardEntity = cardRepository.findCardEntityById(card.getId());
        if(cardEntity.isEmpty()){
            return Either.left(new ApplicationError("Card not found", null, card, null));
        }
        val cardUpdated = CardEntity.builder()
                .id(cardEntity.get().getId())
                .level(cardEntity.get().getLevel())
                .hero(cardEntity.get().getHero())
                .user(cardEntity.get().getUser())
                .experience(cardEntity.get().getExperience() + 1)
                .build();
        return Either.right(CardEntityMapper.toDomain(cardRepository.save(cardUpdated)));
    }

    @Override
    @Transactional
    public Either<ApplicationError, Card> updateLevel(Card card) {
        val level = levelRepository.findByLevel(card.getLevel().getLevel()+1);
        if(level.isEmpty()){
            return Either.left(new ApplicationError("Level not found", null, card, null));
        }
        val cardEntity = cardRepository.findCardEntityById(card.getId());
        if(cardEntity.isEmpty()){
            return Either.left(new ApplicationError("Card not found", null, card, null));
        }
        val cardUpdated = CardEntity.builder()
                .id(cardEntity.get().getId())
                .level(level.get())
                .hero(cardEntity.get().getHero())
                .user(cardEntity.get().getUser())
                .experience(cardEntity.get().getExperience())
                .build();
        return Try(() -> cardRepository.save(cardUpdated))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save card", null, cardUpdated, throwable))
                .map(CardEntityMapper::toDomain);

    }

    @Override
    public Either<ApplicationError, Card> resetExperience(Card card) {
        val cardEntity = cardRepository.findCardEntityById(card.getId());
        if(cardEntity.isEmpty()){
            return Either.left(new ApplicationError("Card not found", null, card, null));
        }
        val cardUpdated = CardEntity.builder()
                .id(cardEntity.get().getId())
                .level(cardEntity.get().getLevel())
                .hero(cardEntity.get().getHero())
                .user(cardEntity.get().getUser())
                .experience(0)
                .build();
        return Either.right(CardEntityMapper.toDomain(cardRepository.save(cardUpdated)));
    }

    @Override
    @Transactional
    public Either<ApplicationError, Card> save(Card o) {
        return Try(() -> cardRepository.save(CardEntityMapper.fromDomain(o)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save card", null, o, throwable))
                .map(CardEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<Card> findById(UUID uuid) {
        return cardRepository.findCardEntityById(uuid)
                .map(CardEntityMapper::toDomain);
    }
}
