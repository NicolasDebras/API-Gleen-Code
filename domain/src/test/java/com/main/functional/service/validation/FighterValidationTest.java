package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.functional.model.Fighter;
import lombok.val;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static com.main.functional.service.validation.FighterValidation.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;


class FighterValidationTest {

    @Test
    void should_validate_fighter() {
        val fighter = validate(Fighter.builder()
                .attackCard(Card.builder().id(UUID.randomUUID()).build())
                .defenseCard(Card.builder().id(UUID.randomUUID()).build())
                .build());
        assertThat(fighter).containsValidInstanceOf(Fighter.class);

    }

    @Test
    void should_validate_fighter_with_null() {
        val fighter = validate(null);
        assertThat(fighter).containsInvalidInstanceOf(ApplicationError.class);
    }

    @Test
    void should_validate_fighter_with_null_attack_card() {
        val fighter = validate(Fighter.builder()
                .attackCard(null)
                .defenseCard(Card.builder().id(UUID.randomUUID()).build())
                .build());
        assertThat(fighter).containsInvalidInstanceOf(ApplicationError.class);
    }

    @Test
    void should_validate_fighter_with_null_defense_card() {
        val fighter = validate(Fighter.builder()
                .attackCard(Card.builder().id(UUID.randomUUID()).build())
                .defenseCard(null)
                .build());
        assertThat(fighter).containsInvalidInstanceOf(ApplicationError.class);
    }

    @Test
    void should_validate_fighter_with_null_attack_card_id() {
        val fighter = validate(Fighter.builder()
                .attackCard(Card.builder().id(null).build())
                .defenseCard(Card.builder().id(UUID.randomUUID()).build())
                .build());
        assertThat(fighter).containsInvalidInstanceOf(ApplicationError.class);
    }

    @Test
    void should_validate_fighter_with_null_defense_card_id() {
        val fighter = validate(Fighter.builder()
                .attackCard(Card.builder().id(UUID.randomUUID()).build())
                .defenseCard(Card.builder().id(null).build())
                .build());
        assertThat(fighter).containsInvalidInstanceOf(ApplicationError.class);
    }

}