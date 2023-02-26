package com.main.functional.service;


import com.main.ApplicationError;
import com.main.functional.model.*;
import com.main.ports.server.HeroPersistenceSpi;
import com.main.ports.server.LevelPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class DrawHeroService {


    private final HeroPersistenceSpi heroPersistenceSpi;

    private final LevelPersistenceSpi levelPersistenceSpi;

    public Either<ApplicationError, Deck> drawDeck(int seed, DeckSetting deckSetting, Double percentageLuckyTotal) {
        Random rand = new Random(seed);
        val heroes = new ArrayList<Hero>();
        val rarityDeckSettings = deckSetting.getRarityDeckSettings();
        for (int i = 0; i < deckSetting.getNumberCard(); i++) {
            val percentageLucky = rand.nextDouble() * percentageLuckyTotal;
            val raritySetting = rarityDeckSettings.stream().filter(rarity -> rarity.getPercentageLucky() >= percentageLucky).findFirst();
            if (raritySetting.isEmpty()) {
                log.error("Not possible to find rarity setting");
                return Either.left(new ApplicationError("Not possible to find rarity setting", null, deckSetting, null));
            }
            val hero = drawHeroWithRarity(raritySetting.get());
            if (hero.isLeft()) {
                log.error("Not possible to draw hero");
                return Either.left(hero.getLeft());
            }
            heroes.add(hero.get());
        }
        val deck = buildDeckWithHero(heroes);
        if (deck.isLeft()) {
            log.error("Not possible to build deck with hero");
            return Either.left(deck.getLeft());
        }
        return Either.right(deck.get());
    }

    private Either<ApplicationError, Deck> buildDeckWithHero(List<Hero> heroes) {
        val level = findLevelByUuid();
        if (level.isLeft()) {
            return Either.left(level.getLeft());
        }
        val cards = new ArrayList<Card>();
        for (Hero hero : heroes) {
            val card = buildCardWithHero(hero, level.get());
            cards.add(card);
        }
        return Either.right(Deck.builder().cards(cards).build());
    }

    private Either<ApplicationError, Level> findLevelByUuid() {
        return levelPersistenceSpi.findByLevel(1)
                .map(Either::<ApplicationError, Level>right).getOrElse(Either.left(new ApplicationError("Not possible to find level", null, 1, null)));
    }


    private Card buildCardWithHero(Hero hero, Level level) {
        return Card.builder()
                        .heroType(hero)
                        .level(level)
                        .build();
    }


    private Either<ApplicationError, Hero> drawHeroWithRarity(RarityDeckSetting raritySetting) {
        return heroPersistenceSpi.findByRarityDraw(raritySetting.getIdRarity())
                .map(
                        Either::<ApplicationError, Hero>right).getOrElse(
                        () -> Either.left(new ApplicationError("Not possible to find rarity", null, raritySetting, null)));
    }
}
