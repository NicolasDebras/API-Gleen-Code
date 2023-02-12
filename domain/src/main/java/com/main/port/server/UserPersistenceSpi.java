package com.main.port.server;

import com.main.ApplicationError;
import com.main.functional.model.User;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {

    Option<User> findByUsername(String username);

}
