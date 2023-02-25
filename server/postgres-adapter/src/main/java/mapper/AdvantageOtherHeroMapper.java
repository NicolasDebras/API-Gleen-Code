package mapper;

import com.main.functional.model.AdvantageOtherHero;
import entity.AdvantageOtherHeroEntity;

public interface AdvantageOtherHeroMapper {

    static AdvantageOtherHero toDomain(AdvantageOtherHeroEntity entity) {
        return AdvantageOtherHero.builder()
                .name(entity.getName())
                .power(entity.getPower())
                .build();
    }

    static AdvantageOtherHeroEntity fromDomain(AdvantageOtherHero domain) {
        return AdvantageOtherHeroEntity.builder()
                .name(domain.getName())
                .power(domain.getPower())
                .build();
    }
}
