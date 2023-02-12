package com.main.functional.service;

import com.main.functional.model.Speciality;
import com.main.port.server.SpecialityPersistanceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FindSpeciality {

    private final SpecialityPersistanceSpi spi;

    public  Speciality find(String speciality) {
        return spi.findByName(speciality).orElseThrow(() -> new IllegalArgumentException("Speciality not found"));
    }
}
