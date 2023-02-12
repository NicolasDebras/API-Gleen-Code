package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Speciality;
import com.main.port.server.HeroCreatePersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FindSpeciality {

    private final HeroCreatePersistenceSpi spi;
    public Either<ApplicationError, Speciality> findSpeciality(String speciality) {
        return spi.findSpeciality(speciality)
                .toEither(() -> new ApplicationError("Speciality not found", null, speciality, null));
    }
}
