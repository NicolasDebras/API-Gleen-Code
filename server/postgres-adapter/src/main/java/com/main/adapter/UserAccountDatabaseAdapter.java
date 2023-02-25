package com.main.adapter;

import com.main.ApplicationError;
import com.main.mapper.UserMapper;
import com.main.functional.model.User;
import com.main.ports.server.UserAccountPersistenceSpi;
import com.main.entity.UserEntity;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.main.repository.UserRepository;

import java.util.UUID;

import static com.main.mapper.UserMapper.fromDomain;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class UserAccountDatabaseAdapter implements UserAccountPersistenceSpi {

    private final UserRepository repository;

    @Override
    @Transactional
    public Either<ApplicationError, User> save(User o) {
        val entity = fromDomain(o);
        return Try(() -> repository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save user", null, o, throwable))
                .map(UserMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<User> findById(UUID uuid) {
        return repository.findUserEntityById(uuid)
                .map(UserMapper::toDomain);
    }


    @Override
    public Either<ApplicationError, User> updateToken(User user) {
        val entity = fromDomain(user);
        val entityUpdated = UserEntity.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .token(entity.getToken()+1)
                .build();
        return Try(() -> repository.save(entityUpdated))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save user", null, entityUpdated, throwable))
                .map(UserMapper::toDomain);
    }
}
