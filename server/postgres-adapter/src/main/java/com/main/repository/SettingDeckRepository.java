package com.main.repository;

import com.main.entity.SettingDeckEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SettingDeckRepository extends JpaRepository<SettingDeckEntity, UUID> {

    Optional<SettingDeckEntity> findByNameDeckType(String name);
}
