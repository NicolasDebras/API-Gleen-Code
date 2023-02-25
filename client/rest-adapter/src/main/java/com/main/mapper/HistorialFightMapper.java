package com.main.dto.mapper;

import com.main.functional.model.HistoricalFight;
import com.main.dto.HistorialFightDto;

public interface HistorialFightMapper {

    static HistorialFightDto toDto(HistoricalFight historicalFight) {
        return new HistorialFightDto(
                historicalFight.getId(),
                historicalFight.getLoser(),
                historicalFight.getDate());
    }
}
