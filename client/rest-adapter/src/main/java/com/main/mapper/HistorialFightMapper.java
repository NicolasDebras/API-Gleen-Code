package com.main.mapper;

import com.main.functional.model.HistoricalFight;
import com.main.dto.HistorialFightDto;

public interface HistorialFightMapper {

    static HistorialFightDto toDto(HistoricalFight historicalFight) {
        return new HistorialFightDto(
                historicalFight.getId(),
                historicalFight.getWinner(),
                historicalFight.getLoser(),
                historicalFight.getDate());
    }
}
