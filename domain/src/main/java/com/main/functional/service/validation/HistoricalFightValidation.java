package com.main.functional.service.validation;

import com.main.ApplicationError;
import io.vavr.control.Validation;

import java.util.UUID;

public interface HistoricalFightValidation {

    static Validation<ApplicationError, UUID> validateIdCard(UUID idCard) {
        return idCard == null ?
                Validation.invalid(new ApplicationError("Id card is required", null, null, null))
                : Validation.valid(idCard);
    }
}
