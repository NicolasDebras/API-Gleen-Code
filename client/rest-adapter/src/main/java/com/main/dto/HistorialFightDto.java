package com.main.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HistorialFightDto(

        UUID idFight,

        CardDto cardWinner,
        CardDto cardLoser,

        String dateFight
) {}
