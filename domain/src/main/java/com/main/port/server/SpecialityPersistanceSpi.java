package com.main.port.server;

import com.main.functional.model.Speciality;

import java.util.Optional;
import java.util.UUID;

public interface SpecialityPersistance extends PersistenceSpi<Speciality, UUID> {

    Optional<Speciality> findByName(String name);


}
