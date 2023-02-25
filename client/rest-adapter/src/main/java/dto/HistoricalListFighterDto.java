package dto;

import com.main.functional.model.Card;

import java.util.List;

public record HistoricalListFighterDto(
        Card cardWinner,

        List<HistorialFightDto> fights
)
{ }
