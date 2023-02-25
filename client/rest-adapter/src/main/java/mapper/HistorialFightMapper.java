package mapper;

import com.main.functional.model.HistoricalFight;
import dto.HistorialFightDto;

public interface HistorialFightMapper {

    static HistorialFightDto toDto(HistoricalFight historicalFight) {
        return new HistorialFightDto(
                historicalFight.getId(),
                historicalFight.getLoser(),
                historicalFight.getDate());
    }
}
