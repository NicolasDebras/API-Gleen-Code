package com.main.adapter;

import com.main.ApplicationError;
import com.main.mapper.RarityEntityMapper;
import com.main.functional.model.Rarity;
import com.main.ports.server.RarityPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.main.repository.RarityRepository;

@Service
@RequiredArgsConstructor
public class RarityDatabaseAdapter implements RarityPersistenceSpi {

    private final RarityRepository repository;

    @Override
    public Either<ApplicationError, Rarity> save(Rarity o) {
        return null;
    }

    @Override
    public Option<Rarity> findById(String s) {
        return null;
    }

    @Override
    public Option<Rarity> findByName(String name) {
        return repository.findByName(name)
                .map(RarityEntityMapper::toDomain);
    }
}
