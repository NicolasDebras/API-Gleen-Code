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
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SpecialityDatabaseAdapter implements SpecialityPersistenceSpi {

    private final SpecialityRepository repository;

    @Override
    @Transactional
    public Either<ApplicationError, Speciality> save(Speciality o) {
        return null;
    }

    @Override
    public Option<Speciality> findById(String s) {
        return null;
    }

    @Override
    @Transactional
    public Option<Speciality> findByName(String name) {
        return repository.findByName(name)
                .map(SpecialityEntityMapper::toDomain);
    }
}
