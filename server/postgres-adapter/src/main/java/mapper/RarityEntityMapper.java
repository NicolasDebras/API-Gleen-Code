package mapper;

import com.main.functional.model.Rarity;
import entity.RarityEntity;

public interface RarityEntityMapper{

    static Rarity toDomain(RarityEntity entity) {
        return Rarity.builder()
                .percentage(entity.getPercentage())
                .name(entity.getName())
                .build();
    }

    static RarityEntity fromDomain(Rarity domain) {
        return RarityEntity.builder()
                .percentage(domain.getPercentage())
                .name(domain.getName())
                .build();
    }
}
