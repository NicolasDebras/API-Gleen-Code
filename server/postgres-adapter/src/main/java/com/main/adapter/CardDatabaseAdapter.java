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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardDatabaseAdapter implements CardPersistenceSpi {

    private final CardRepository cardRepository;

    private final LevelRepository levelRepository;
    @Override
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
    public Either<ApplicationError, Card> updateLevel(Card card) {
        val level = levelRepository.findByLevel(card.getLevel().getLevel()+1);
        if(level.isEmpty()){
            return Either.left(new ApplicationError("Level not found", null, card, null));
        }
        return cardRepository.findCardEntityById(card.getId())
                .map(cardEntity -> {
                    cardEntity.setLevel(level.get());
                    return cardRepository.save(cardEntity);
                })
                .map(CardEntityMapper::toDomain)
                .toEither(new ApplicationError("Card not found", null, card, null));
    }

    @Override
    public Either<ApplicationError, Card> save(Card o) {
        val card = cardRepository.save(CardEntityMapper.fromDomain(o));
        return Either.right(CardEntityMapper.toDomain(card));
    }

    @Override
    public Option<Card> findById(UUID uuid) {
        return cardRepository.findCardEntityById(uuid)
                .map(CardEntityMapper::toDomain);
    }
}
