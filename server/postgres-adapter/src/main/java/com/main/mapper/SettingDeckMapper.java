package com.main.mapper;

import com.main.entity.SettingDeckEntity;
import com.main.functional.model.DeckSetting;

public interface SettingDeckMapper {

    static DeckSetting toDomain(SettingDeckEntity entity) {
        return DeckSetting.builder()
                .nameDeckType(entity.getNameDeckType())
                .token(entity.getToken())
                .numberCard(entity.getNumberCard())
                .rarityDeckSettings(entity.getRarityDeckSettings().stream()
                        .map(RarityDeckSettingMapper::toDomain)
                        .toList())
                .build();
    }
}
