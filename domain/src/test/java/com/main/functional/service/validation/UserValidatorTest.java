package com.main.functional.service.validation;

import com.main.functional.model.User;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.main.functional.service.validation.UserValidator.validate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class UserValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"Aristide", "Nicolas"})
    @DisplayName("Validate user creation if username is under bigger 3 and smaller than 20")
    void should_validate(String username) {
        val actual = validate(User.builder().username(username).build());
        assertThat(actual).isNotEmpty();
    }


    @ParameterizedTest
    @ValueSource(strings = {"ari", "abcdefghijklmnopqrst"})
    @DisplayName("Validate user creation if username is under bigger 3 and smaller than 20")
    void should_not_validate(String username) {
        val actual = validate(User.builder().username(username).build());
        assertThat(actual).isEmpty();
    }



}