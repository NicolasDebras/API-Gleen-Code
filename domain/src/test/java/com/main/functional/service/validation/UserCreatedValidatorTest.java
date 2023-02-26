package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.User;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static com.main.functional.service.validation.UserCreatedValidator.validate;

class UserCreatedValidatorTest {


    @ParameterizedTest
    @ValueSource( strings = {"test1", "aristide_fumo"} )
    void should_validate(String validUsername) {
        val actual = validate(User.builder().username(validUsername).build());
        assertThat(actual).containsValidInstanceOf(User.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource( strings = {"lor", "azertyuiopmlkjh", "09876543210987654321"} )
    void should_not_validate(String invalidUsername) {
        val actual = validate(User.builder().username(invalidUsername).build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }


}