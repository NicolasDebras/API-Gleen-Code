package com.main.repository;

import com.main.entity.HeroEntity;
import com.main.entity.RarityEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {

    @Query(value = "SELECT id, name, rarity_id, speciality_id FROM hero WHERE rarity_id = ?1 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Option<HeroEntity> findRandomHeroByRarity(UUID rarityId);

    Optional<HeroEntity> findByName(String name);

}
