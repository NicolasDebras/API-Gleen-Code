package com.main.port.server;

import com.main.functional.model.Speciality;

import java.util.Optional;
import java.util.UUID;

public interface SpecialityPersistanceSpi extends PersistenceSpi<Speciality, UUID> {

    Optional<Speciality> findByName(String name);


}
