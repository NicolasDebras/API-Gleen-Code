package com.main.functional.service;


import com.main.functional.model.User;
import com.main.functional.service.validation.UserValidator;
import com.main.port.client.UserCreatorApi;
import com.main.port.server.UserPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserCreatorService implements UserCreatorApi {

    private final UserPersistenceSpi spi;

    @Override
    public Optional<User> create(User user) {
        return UserValidator.validate(user)
                .map(userValidator -> spi.save(user));
    }
}

