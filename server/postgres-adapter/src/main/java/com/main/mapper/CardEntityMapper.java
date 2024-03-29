package com.main.mapper;

import com.main.functional.model.Card;
import com.main.functional.model.Deck;
import com.main.functional.model.Hero;
import com.main.entity.CardEntity;
import lombok.val;

import java.util.List;


public interface CardEntityMapper {

    static Card toDomain(CardEntity entity) {
        return Card.builder()
                .id(entity.getId())
                .user(UserMapper.toDomain(entity.getUser()))
                .heroType(HeroMapper.toDomain(entity.getHero()))
                .level(LevelMapper.toDomain(entity.getLevel()))
                .experience(entity.getExperience())
                .build();
    }

    static CardEntity fromDomain(Card domain) {
        return CardEntity.builder()
                .id(domain.getId())
                .user(UserMapper.fromDomain(domain.getUser()))
                .hero(HeroMapper.fromDomain(domain.getHeroType()))
                .level(LevelMapper.fromDomain(domain.getLevel()))
                .experience(domain.getExperience())
                .build();
    }

    static Deck toDomainDeck(List<CardEntity> entity) {
        return Deck.builder()
                .cards(entity.stream()
                        .map(CardEntityMapper::toDomain)
                        .toList())
                .build();
    }
}
