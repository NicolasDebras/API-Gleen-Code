package com.main.functional.service.validation;


import com.main.functional.model.Hero;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.main.functional.service.validation.HeroCreateValidator.validate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HeroValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"Aristide", "Nicolas"})
    @DisplayName("Validate hero creation if name is under bigger 3 and smaller than 20")
    void should_validate_name(String name) {
        val actual = validate(Hero
                .builder()
                .name(name)
                .speciality("Assassin")
                .rarity("Common")
                .build());
        assertThat(actual).isNotEmpty();

    }

    @ParameterizedTest
    @ValueSource(strings = {"ari", "abcdefghijklmnopqrst"})
    @DisplayName("Validate hero creation if name is under bigger 3 and smaller than 20")
    void should_not_validate_name(String name) {
        val actual = validate(Hero
                .builder()
                .name(name)
                .speciality("Assassin")
                .rarity("Common")
                .build());
        assertThat(actual).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Common", "Rare", "Legendary"})
    @DisplayName("Validate hero creation if rarity is not null")
     void should_validate_rarity(String rarity) {
        val actual = validate(Hero
                .builder()
                .name("Aristide")
                .speciality("Assassin")
                .rarity(rarity)
                .build());
        assertThat(actual).isNotEmpty();
    }

    @Test
    @DisplayName("Validate hero creation fail if rarity is null")
    void should_not_validate_rarity() {
        val actual = validate(Hero
                .builder()
                .name("Aristide")
                .speciality("Assassin")
                .rarity(null)
                .build());
        assertThat(actual).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Assassin", "Tank", "Wizard"})
    @DisplayName("Validate hero creation if speciality is not null and good value")
    void should_validate_speciality(String speciality) {
        val actual = validate(Hero
                .builder()
                .name("Aristide")
                .speciality(speciality)
                .rarity("Common")
                .build());
        assertThat(actual).isNotEmpty();
    }




    @Test
    @DisplayName("Validate hero creation fail if speciality is null")
    void should_not_validate_speciality() {
        val actual = validate(Hero
                .builder()
                .name("Aristide")
                .speciality(null)
                .rarity("Common")
                .build());
        assertThat(actual).isEmpty();
    }




}