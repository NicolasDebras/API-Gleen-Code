package com.main.mapper;

import com.main.functional.model.HistoricalFight;
import com.main.entity.HistoricalFightEntity;

import java.util.List;

public interface HistoricalFightMapper {

    static HistoricalFight toDomain(HistoricalFightEntity entity) {
        return HistoricalFight.builder()
                .id(entity.getId())
                .winner(CardEntityMapper.toDomain(entity.getWinner()))
                .loser(CardEntityMapper.toDomain(entity.getLoser()))
                .date(entity.getDate())
                .build();
    }


    static HistoricalFightEntity fromDomain(HistoricalFight historicalFight) {
        return HistoricalFightEntity.builder()
                .id(historicalFight.getId())
                .date(historicalFight.getDate())
                .loser(CardEntityMapper.fromDomain(historicalFight.getLoser()))
                .winner(CardEntityMapper.fromDomain(historicalFight.getWinner()))
                .build();
    }

    static List<HistoricalFight> toDomainList(List<HistoricalFightEntity> entities) {
        return entities.stream()
                .map(HistoricalFightMapper::toDomain)
                .toList();
    }
}
