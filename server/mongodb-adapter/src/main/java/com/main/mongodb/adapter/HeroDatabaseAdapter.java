package com.main.mongodb.adapter;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.Hero;
import com.main.domain.ports.server.HeroPersistenceSpi;
import com.main.mongodb.mapper.HeroEntityMapper;
import com.main.mongodb.repository.HeroRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import static io.vavr.API.Try;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.main.mongodb.mapper.HeroEntityMapper.fromDomain;

@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroPersistenceSpi {

    private final HeroRepository repository;
    @Override
    @Transactional
    public Either<ApplicationError, Hero> save(Hero o) {
        val entity = fromDomain(o);
        return  Try(()-> repository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save hero", null, o, throwable))
                .map(HeroEntityMapper::toDomain);

    }

    @Override
    public Option<Hero> findById(UUID uuid) {
        return null;
    }
}
