package com.main.functional.service;

import com.main.functional.model.User;
import com.main.port.server.UserPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserCreatorServiceTest {

    @InjectMocks
    private UserCreatorService service;

    @Mock
    private UserPersistenceSpi spi;


    @Test
    @DisplayName("Create user is good")
    void should_create_user() {
        val user = User.builder().username("aristide")
                .build();
        when(spi.save(user)).thenReturn(user);
        val actual = service.create(user);
        assertThat(actual).contains(user);
    }

    @Test
    @DisplayName("Create user is bad")
    void should_not_create_user() {
        val user = User.builder().username("aristide")
                .build();
        when(spi.save(user)).thenReturn(null);
        val actual = service.create(user);

    }


}