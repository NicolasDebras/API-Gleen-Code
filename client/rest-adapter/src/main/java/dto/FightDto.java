package dto;

import java.util.UUID;

public record FightDto(
        UUID idCardAttacker,
        UUID idCardDefender
) { }
