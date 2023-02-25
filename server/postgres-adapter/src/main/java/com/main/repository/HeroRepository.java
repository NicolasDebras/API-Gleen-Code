package com.main.repository;

import com.main.entity.HeroEntity;
import com.main.entity.RarityEntity;
import com.main.functional.model.Rarity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface HeroRepository extends JpaRepository<HeroEntity, UUID> {

    Option<HeroEntity> findByRarity(RarityEntity rarity);



}
