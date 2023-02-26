package com.main.mapper;

import com.main.entity.RarityDeckSettingEntity;
import com.main.functional.model.RarityDeckSetting;

public interface RarityDeckSettingMapper {


    static RarityDeckSetting toDomain(RarityDeckSettingEntity entity) {
        return RarityDeckSetting.builder()
                .idRarity(entity.getId())
                .percentageLucky(entity.getPercentageLucky())
                .build();
    }
}
