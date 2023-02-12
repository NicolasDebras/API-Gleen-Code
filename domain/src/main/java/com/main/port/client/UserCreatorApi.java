package com.main.port.client;

import com.main.functional.model.User;

import java.util.Optional;

public interface UserCreatorApi {


    Optional<User> create(User user);

}
