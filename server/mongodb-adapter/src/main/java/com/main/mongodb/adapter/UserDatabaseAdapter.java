package com.main.mongodb.adapter;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.User;
import com.main.domain.ports.server.UserPersistenceSpi;
import com.main.mongodb.mapper.UserEntityMapper;
import com.main.mongodb.repository.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.main.mongodb.mapper.UserEntityMapper.fromDomain;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class UserDatabaseAdapter implements UserPersistenceSpi {

    private final UserRepository repository;
    @Override
    public Either<ApplicationError, User> save(User o) {
        val entity = fromDomain(o);
        return Try(()-> repository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save User", null, o, throwable))
                .map(UserEntityMapper::toDomain);
    }

    @Override
    public Option<User> findById(UUID uuid) {
        return null;
    }
}
