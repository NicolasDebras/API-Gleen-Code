package com.main.adapter;

import com.main.ApplicationError;
import com.main.mapper.SpecialityEntityMapper;
import com.main.functional.model.Speciality;
import com.main.ports.server.SpecialityPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import com.main.repository.SpecialityRepository;


@Service
@RequiredArgsConstructor
public class SpecialityDatabaseAdapter implements SpecialityPersistenceSpi {

    private final SpecialityRepository repository;
    @Override
    public Either<ApplicationError, Speciality> save(Speciality o) {
        val speciality = repository.save(SpecialityEntityMapper.fromDomain(o));
        return Either.right(SpecialityEntityMapper.toDomain(speciality));
    }

    @Override
    public Option<Speciality> findById(String s) {
        return null;
    }

    @Override
    public Option<Speciality> findByName(String name) {
        return repository.findByName(name)
                .map(SpecialityEntityMapper::toDomain);
    }
}
