package com.main.mapper;

import com.main.functional.model.RarityDeckSetting;
import com.main.entity.RarityDeckSettingEntity;

import java.util.UUID;

public interface RarityDeckSettingRepository {

    static RarityDeckSetting toDomain(RarityDeckSettingEntity entity) {
        return RarityDeckSetting.builder()
                .idRarity(UUID.randomUUID())
                .percentageLucky(entity.getPercentageLucky())
                .build();
    }

    static RarityDeckSettingEntity fromDomain(RarityDeckSetting domain) {
        return RarityDeckSettingEntity.builder()
                .id(UUID.randomUUID())
                .percentageLucky(domain.getPercentageLucky())
                .build();
    }
}
