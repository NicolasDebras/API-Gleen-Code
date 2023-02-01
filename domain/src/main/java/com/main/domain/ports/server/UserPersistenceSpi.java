package com.main.domain.ports.server;

import com.main.domain.functionnal.model.User;

import java.util.UUID;

public interface UserPersistenceSpi extends PersistenceSpi<User, UUID> {
}
