package com.main.functional.service.validation;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HistoricalFightValidationTest {

    @Test
    void validateIdCard() {
        val idCard = UUID.randomUUID();
        val result = HistoricalFightValidation.validateIdCard(idCard);
        assertTrue(result.isValid());
    }

    @Test
    void invalidIdCardNull() {
        val result = HistoricalFightValidation.validateIdCard(null);
        assertTrue(result.isInvalid());
    }

}