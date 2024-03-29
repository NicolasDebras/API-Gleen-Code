package com.main.repository;

import com.main.entity.AdvantageOtherHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface AdvantageOtherHeroRepository extends JpaRepository<AdvantageOtherHeroEntity, UUID>{


}
