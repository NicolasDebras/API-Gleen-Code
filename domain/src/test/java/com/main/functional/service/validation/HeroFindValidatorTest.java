package com.main.functional.service.validation;

import com.main.ApplicationError;
import com.main.functional.model.HeroFind;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.main.functional.service.validation.HeroFindValidator.validate;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
class HeroFindValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49})
    void should_validate_LimitHeroFind(int limit) {
        val actual = validate(HeroFind.builder()
                .limit(limit)
                .build());
        assertThat(actual).containsValidInstanceOf(HeroFind.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 50})
    void should_not_validate_LimitHeroFind(int limit) {
        val actual = validate(HeroFind.builder()
                .limit(limit)
                .build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5,})
    void should_validate_OffsetHeroFind(int offset) {
        val actual = validate(HeroFind.builder()
                .offset(offset)
                .limit(1)
                .build());
        assertThat(actual).containsValidInstanceOf(HeroFind.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3, -4, -5,})
    void should_not_validate_OffsetHeroFind(int offset) {
        val actual = validate(HeroFind.builder()
                .offset(offset)
                .limit(1)
                .build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }



}