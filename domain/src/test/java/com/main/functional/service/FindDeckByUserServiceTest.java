package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.functional.model.Deck;
import com.main.functional.model.User;
import com.main.ports.server.DeckPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
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
class FindDeckByUserServiceTest {

    @Mock
    private UserAccountPersistenceSpi userAccountPersistenceSpi;

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @InjectMocks
    private FindDeckByUserService findDeckByUserService;

    @Test
    @DisplayName("Should return a deck")
    void shouldReturnADeck() {
        val idUser = UUID.randomUUID();
        val listCard = List.of(
                Card.builder()
                        .id(UUID.randomUUID())
                        .build(),
                Card.builder()
                        .id(UUID.randomUUID())
                        .build()
        );
        val deck = Deck.builder()
                .cards(listCard)
                .build();
        when(deckPersistenceSpi.find(idUser)).thenReturn(Either.right(deck));
        when(userAccountPersistenceSpi.findById(idUser)).thenReturn(Option.of(User.builder().build()));
        val result = findDeckByUserService.find(idUser);
        assertTrue(result.isRight());
        assertEquals(deck, result.get());
    }

    @Test
    @DisplayName("Should return an error when the user is not found")
    void shouldReturnAnErrorWhenTheUserIsNotFound() {
        val idUser = UUID.randomUUID();
        when(userAccountPersistenceSpi.findById(idUser)).thenReturn(Option.none());
        val actual = findDeckByUserService.find(idUser);
        assertTrue(actual.isLeft());
        val error = new ApplicationError("Not possible to find user", null, idUser, null);
        assertThat(actual).containsOnLeft(error);
    }

    @Test
    @DisplayName("Should return an error when the deck is not found or empty")
    void shouldReturnAnErrorWhenTheDeckIsNotFound(){
        val idUser = UUID.randomUUID();
        val error = new ApplicationError("Not possible to find deck", null, idUser, null);
        when(deckPersistenceSpi.find(idUser)).thenReturn(Either.left(error));
        when(userAccountPersistenceSpi.findById(idUser)).thenReturn(Option.of(User.builder().build()));
        val result = findDeckByUserService.find(idUser);
        assertTrue(result.isLeft());
        assertThat(result).containsOnLeft(error);
    }






}