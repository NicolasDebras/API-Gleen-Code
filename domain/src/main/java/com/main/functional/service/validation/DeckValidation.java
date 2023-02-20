package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.DeckDTO;
import io.vavr.control.Validation;

public interface DeckValidation {

    static Validation<ApplicationError, DeckDTO> validate(DeckDTO deck){
        return deck.getIdUser() != null && deck.getNameDeck() != null
                && deck.getNameDeck().length() > 3 && deck.getNameDeck().length() < 15
                ? Validation.valid(deck)
                : Validation.invalid(new ApplicationError("Invalid deck", null, deck, null));
    }
}
