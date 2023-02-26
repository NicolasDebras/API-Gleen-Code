package com.main.mapper;

import com.main.functional.model.HistoricalFight;
import com.main.dto.HistorialFightDto;

public interface HistorialFightMapper {

    static HistorialFightDto toDto(HistoricalFight historicalFight) {
        return new HistorialFightDto(
                historicalFight.getId(),
                CardMapper.toDto(historicalFight.getWinner()),
                CardMapper.toDto(historicalFight.getLoser()),
                historicalFight.getDate().toString());
    }
}
