package repository;

import entity.HeroEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {

    Option<HeroEntity> findByRarityDraw(UUID idRarity);

    Option<List<HeroEntity>> findAll();

    Option<HeroEntity> findById(UUID uuid);

}
