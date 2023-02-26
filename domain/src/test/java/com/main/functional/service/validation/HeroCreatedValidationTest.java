package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.main.functional.service.validation.HeroCreatedValidation.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

class HeroCreatedValidationTest {

    @ParameterizedTest
    @ValueSource( strings = {"test", "aristide_fumo"} )
    void should_validate(String validUsername) {
        val actual = validate(Hero.builder()
                .name(validUsername)
                .speciality(Speciality.builder().name("test").build())
                .rarity(Rarity.builder().name("test").build())
                .build());
        assertThat(actual).containsValidInstanceOf(Hero.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource( strings = {"lor", "azertyuiopmlkjh", "09876543210987654321"} )
    void should_not_validate(String invalidUsername) {
        val actual = validate(Hero.builder()
                .name(invalidUsername)
                .speciality(Speciality.builder().name("test").build())
                .rarity(Rarity.builder().name("test").build())
                .build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }




}