package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.User;
import com.main.ports.server.UserAccountPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountCreatorServiceTest {
    @InjectMocks private UserAccountCreatorService service;

    @Mock private UserAccountPersistenceSpi spi;

    @Test
    @DisplayName("should create user")
    void should_create_user() {
        val given = User.builder().username("test").build();
        when(spi.save(given)).thenReturn(Right(given));

        val actual = service.create(given);
        assertThat(actual).containsRightSame(given);
    }

    @Test
    @DisplayName("should not create user if username is invalid")
    void should_not_create_user_if_username_is_invalid() {
        val given = User.builder().username("tes").build();
        val actual = service.create(given);
        assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
        verifyNoInteractions(spi);
    }

    @Test
    @DisplayName("should not create user if technical error occurred in adapter")
    void should_not_create_user_if_technical_error_occurred_in_adapter() {
        val given = User.builder().username("test").build();
        val error = new ApplicationError("An error occurred", null, null, null);
        when(spi.save(given)).thenReturn(Left(error));
        val actual = service.create(given);
        assertThat(actual).containsLeftSame(error);
    }




}