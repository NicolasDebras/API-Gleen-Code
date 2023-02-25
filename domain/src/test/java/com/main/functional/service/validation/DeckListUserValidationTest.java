package com.main.functional.service.validation;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeckListUserValidationTest {

    @Test
    void validate() {
        val idUser = UUID.randomUUID();
        val actual = DeckListUserValidation.validate(idUser);
        assertTrue(actual.isValid());
    }

    @Test
    void validateNull() {
        val actual = DeckListUserValidation.validate(null);
        assertTrue(actual.isInvalid());
    }


}