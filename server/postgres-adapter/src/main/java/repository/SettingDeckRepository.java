package repository;

import entity.SettingDeckEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettingDeckRepository extends JpaRepository<SettingDeckEntity, UUID> {

    Option<SettingDeckEntity> findByName(String name);
}
