package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.functional.model.HistoricalFight;
import com.main.ports.server.CardPersistenceSpi;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoricalFightServiceTest {

    @Mock
    private HistoricalFightPersistenceSpi historicalFightPersistenceSpi;

    @Mock
    private CardPersistenceSpi cardPersistenceSpi;

    @InjectMocks
    private HistoricalFightService historicalFightService;

    @Test
    @DisplayName("Should return a list of historical fight")
    void shouldReturnAListOfHistoricalFight() {
        val idCard = UUID.randomUUID();
        val card = Card.builder()
                .id(idCard)
                .build();
        val listHistoricalFight = List.of(
                HistoricalFight.builder()
                        .id(UUID.randomUUID())
                        .winner(card)
                        .build(),
                HistoricalFight.builder()
                        .id(UUID.randomUUID())
                        .winner(card)
                        .build()
        );
        when(cardPersistenceSpi.findById(idCard)).thenReturn(Option.of(card));
        when(historicalFightPersistenceSpi.findAllByIDCard(idCard)).thenReturn(Either.right(listHistoricalFight));
        val result = historicalFightService.find(idCard);
        assertTrue(result.isRight());
        assertThat(result).containsOnRight(listHistoricalFight);
    }

    @Test
    @DisplayName("Should return a application error")
    void shouldReturnApplicationError() {
        val idCard = UUID.randomUUID();
        val card = Card.builder()
                .id(idCard)
                .build();
        val error = new ApplicationError("Not possible to find historical fight", null, idCard, null);
        when(cardPersistenceSpi.findById(idCard)).thenReturn(Option.of(card));
        when(historicalFightPersistenceSpi.findAllByIDCard(idCard)).thenReturn(Either.left(error));
        val result = historicalFightService.find(idCard);
        assertTrue(result.isLeft());
        assertThat(result).containsOnLeft(error);

    }

    @Test
    @DisplayName("Should return a application error when id card is invalid")
    void shouldReturnAApplicationErrorWhenIdCardIsInvalid() {
        val idCard = UUID.randomUUID();
        when(cardPersistenceSpi.findById(idCard)).thenReturn(Option.none());
        val error = new ApplicationError("Not possible to find card", null, idCard, null);
        val result = historicalFightService.find(idCard);
        assertTrue(result.isLeft());
        assertThat(result).containsOnLeft(error);
    }

}