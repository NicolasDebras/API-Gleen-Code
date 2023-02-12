package com.main.port.server;

import com.main.ApplicationError;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.Optional;

public interface PersistenceSpi<T, ID> {
    T save(T o);

    Option<T> findById(ID id);
}