package com.main.functional.service.validation;

import com.main.ApplicationError;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.main.functional.model.DeckDTO;

import java.util.UUID;

import static com.main.functional.service.validation.DeckValidation.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class DeckValidationTest {

    @ParameterizedTest
    @ValueSource( strings = {"legendary", "common"} )
    void should_validate_name_deck(String validNameDeck) {
        val actual = validate(DeckDTO.builder()
                .idUser(UUID.randomUUID())
                .nameDeck(validNameDeck)
                .build());
        assertThat(actual).containsValidInstanceOf(DeckDTO.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource( strings = {"lor", "azertyuiopmlkjh", "09876543210987654321"} )
    void should_not_validate_name_deck(String invalidNameDeck) {
        val actual = validate(DeckDTO.builder()
                .idUser(UUID.randomUUID())
                .nameDeck(invalidNameDeck)
                .build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }

    @Test
    @DisplayName("should not validate  deck when id is null")
    void should_not_validate_id_user() {
        val actual = validate(DeckDTO.builder()
                .nameDeck("test")
                .build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }


}