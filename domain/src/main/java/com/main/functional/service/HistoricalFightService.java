package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.functional.model.HistoricalFight;
import com.main.functional.service.validation.HistoricalFightValidation;
import com.main.ports.client.FindHistoricalFightCardApi;
import com.main.ports.server.CardPersistenceSpi;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class HistoricalFightService implements FindHistoricalFightCardApi {

    private final HistoricalFightPersistenceSpi historicalFightPersistenceSpi;

    private final CardPersistenceSpi cardPersistenceSpi;

    @Override
    public Either<ApplicationError, List<HistoricalFight>> find(UUID idCard) {
        return HistoricalFightValidation.validateIdCard(idCard)
                .toEither(() -> new ApplicationError("Invalid idCard", null, null, null))
                .flatMap(this::findCard)
                .flatMap(card -> historicalFightPersistenceSpi.findAllByIDCard(idCard)
                        .toEither(() -> new ApplicationError("Not possible to find historical fight", null, idCard, null)));
    }

    private Either<ApplicationError, Card> findCard(UUID idCard) {
        return cardPersistenceSpi.findById(idCard)
                .toEither(() -> new ApplicationError("Not possible to find card", null, idCard, null));
    }


}
