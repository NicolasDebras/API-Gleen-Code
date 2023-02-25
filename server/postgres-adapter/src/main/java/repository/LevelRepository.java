package repository;

import entity.LevelEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface LevelRepository extends JpaRepository<LevelEntity, UUID> {

    Option<LevelEntity> findByLevel(int level);
}
