package dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record CardDto(

        UUID id,
        String name,

        String rarity,

        String speciality,

        int health,

        int power,

        int level,

        int exp,

        int armor


) {}
