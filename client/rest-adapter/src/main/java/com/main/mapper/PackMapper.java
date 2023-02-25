package com.main.dto.mapper;

import com.main.functional.model.Deck;
import com.main.functional.model.DeckDTO;
import com.main.dto.CardDto;
import com.main.dto.OpenPackDto;
import com.main.dto.PackDto;

import java.util.List;


public interface PackMapper {

    static DeckDTO toDomain(OpenPackDto openPackDto) {
        return DeckDTO.builder()
                .idUser(openPackDto.idUser())
                .nameDeck(openPackDto.packName())
                .build();

    }

    static PackDto toDto(Deck deck) {
        List<CardDto> cardDto = deck.getCards().stream()
                .map(CardMapper::toDto)
                .toList();
        return new PackDto(cardDto);
    }
}
