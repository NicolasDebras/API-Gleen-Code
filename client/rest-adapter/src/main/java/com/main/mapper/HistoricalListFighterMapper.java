package com.main.dto.mapper;

import com.main.functional.model.HistoricalFight;
import com.main.dto.HistoricalListFighterDto;

import java.util.List;

public interface HistoricalListFighterMapper {

    static HistoricalListFighterDto toDto(List<HistoricalFight> historicalFights) {
        return new HistoricalListFighterDto(
                historicalFights.get(0).getWinner(),
                historicalFights.stream()
                        .map(HistorialFightMapper::toDto)
                        .toList()
        );
    }
}