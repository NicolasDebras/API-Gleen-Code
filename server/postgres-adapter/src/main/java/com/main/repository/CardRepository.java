package com.main.repository;

import com.main.entity.CardEntity;
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
public interface CardRepository extends JpaRepository<CardEntity, UUID> {


    Optional<List<CardEntity>> findAllByUser(UUID idUser);


    Option<CardEntity> findByCardId(UUID id);

}
