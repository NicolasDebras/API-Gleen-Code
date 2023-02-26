package com.main.dto;

import com.main.functional.model.Card;

import java.time.LocalDateTime;
import java.util.UUID;

public record HistorialFightDto(

        UUID idFight,

        Card cardWinner,
        Card cardLoser,

        LocalDateTime dateFight
) {}
