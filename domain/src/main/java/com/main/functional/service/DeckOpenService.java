package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.*;
import com.main.functional.service.validation.DeckValidation;
import com.main.ports.client.DeckOpenApi;
import com.main.ports.server.*;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
public class DeckOpenService implements DeckOpenApi {

    private final UserAccountPersistenceSpi userAccountPersistenceSpi;

    private final SettingDeckPersistenceSpi deckSettingPersistenceSpi;

    private final DrawHeroService drawHeroService;

    private final DeckPersistenceSpi deckPersistenceSpi;

    private static final int INTEGER_MAX = 2147483647;


    @Override
    public Either<ApplicationError, Deck> create(DeckDTO deck) {
        val deckValidated = DeckValidation.validate(deck);
        if (!deckValidated.isValid()) {
            log.error("Not valid Deck");
            return Either.left(new ApplicationError("Not valid Deck", null, deck, null));
        }
        val deckSetting = findDeckSetting(deck.getNameDeck());
        if (deckSetting.isLeft()) {
            log.error("Not possible to find deck setting");
            return Either.left(deckSetting.getLeft());
        }
        val getDeckSetting = deckSetting.get();
        if (!isPossibleToOpenDeck(deck.getIdUser(), getDeckSetting.getToken())) {
            log.error("Not possible to open deck");
            return Either.left(new ApplicationError("Not possible to open deck", null, deck, null));
        }
        val percentageLuckyTotal = calculatePercentage(getDeckSetting.getRarityDeckSettings());
        val seed = new Random().nextInt(INTEGER_MAX);
        val heroes = drawHeroService.drawDeck(seed, getDeckSetting, percentageLuckyTotal);
        if (heroes.isLeft()) {
            log.error("Not possible to draw heroes");
            return Either.left(heroes.getLeft());
        }
        val deckCreated = deckPersistenceSpi.save(deck.getIdUser(), heroes.get());
        if (deckCreated.isLeft()) {
            log.error("Not possible to save deck");
            return Either.left(deckCreated.getLeft());
        }
        return Either.right(deckCreated.get());
    }


    private Boolean isPossibleToOpenDeck(UUID idUser, int numberTokenOpenDeck) {
        return userAccountPersistenceSpi.findById(idUser).map(user -> user.getToken() >= numberTokenOpenDeck).getOrElse(false);
    }

    private Either<ApplicationError, DeckSetting> findDeckSetting(String nameTypeDeck) {
        return deckSettingPersistenceSpi.findByName(nameTypeDeck)
                .map(Either::<ApplicationError, DeckSetting>right).getOrElse(Either.left(new ApplicationError("Not possible to find deck setting", null, nameTypeDeck, null)));
    }

    private Double calculatePercentage(List<RarityDeckSetting> rarityDeckSettings) {
        return rarityDeckSettings.stream().mapToDouble(RarityDeckSetting::getPercentageLucky).sum();
    }





}
