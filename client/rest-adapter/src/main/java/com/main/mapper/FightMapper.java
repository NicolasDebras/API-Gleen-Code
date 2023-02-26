package com.main.mapper;

import com.main.functional.model.Card;
import com.main.functional.model.Fighter;
import com.main.dto.FightDto;

public interface FightMapper {

    static Fighter  toDomain(FightDto fightDto) {
        return Fighter.builder()
                .attackCard(
                        Card.builder()
                                .id(fightDto.idCardAttacker())
                                .build())
                .defenseCard(
                        Card.builder()
                                .id(fightDto.idCardDefender())
                                .build())
                .build();
    }
}
