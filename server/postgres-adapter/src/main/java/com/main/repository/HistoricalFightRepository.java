package com.main.repository;

import com.main.entity.CardEntity;
import com.main.entity.HistoricalFightEntity;
import com.main.functional.model.Card;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface HistoricalFightRepository extends JpaRepository<HistoricalFightEntity, UUID> {

    Option<List<HistoricalFightEntity>>  findByWinner(CardEntity card);

    Option<List<HistoricalFightEntity>>  findByLoser(CardEntity card);
}
