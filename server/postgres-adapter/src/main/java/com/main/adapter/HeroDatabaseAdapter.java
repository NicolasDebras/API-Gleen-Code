package com.main.adapter;

import com.main.ApplicationError;
import com.main.entity.RarityEntity;
import com.main.functional.model.Hero;
import com.main.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import com.main.mapper.HeroMapper;
import org.springframework.stereotype.Service;
import com.main.repository.HeroRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroPersistenceSpi {

    private final HeroRepository repository;

    @Override
    public Option<Hero> findByRarityDraw(UUID idRarity) {
        return repository.findByRarity(RarityEntity.builder().id(idRarity).build())
                .map(HeroMapper::toDomain);
    }

    @Override
    public Option<List<Hero>> findAll() {
        val heroes = repository.findAll();
        if (heroes.isEmpty()) {
            return Option.none();
        }
        return Option.of(HeroMapper.toDomainList(heroes));
    }

    @Override
    public Either<ApplicationError, Hero> save(Hero o) {
        val hero = repository.save(HeroMapper.fromDomain(o));
        return Either.right(HeroMapper.toDomain(hero));
    }

    @Override
    public Option<Hero> findById(UUID uuid) {
        val hero = repository.findById(uuid);
        if (hero.isEmpty()) {
            return Option.none();
        }
        return Option.of(HeroMapper.toDomain(hero.get()));
    }
}
