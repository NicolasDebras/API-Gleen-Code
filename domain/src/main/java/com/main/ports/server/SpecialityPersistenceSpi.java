package com.main.ports.server;

import com.main.functional.model.Speciality;
import io.vavr.control.Option;

public interface SpecialityPersistenceSpi extends PersistenceSpi<Speciality, String>{

    Option<Speciality> findByName(String name);
}
