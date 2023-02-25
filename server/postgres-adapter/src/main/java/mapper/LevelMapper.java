package mapper;

import com.main.functional.model.Level;
import entity.LevelEntity;

public interface LevelMapper {

    static Level toDomain(LevelEntity entity) {
        return Level.builder()
                .id(entity.getId())
                .level(entity.getLevel())
                .build();
    }

    static LevelEntity fromDomain(Level domain) {
        return LevelEntity.builder()
                .id(domain.getId())
                .level(domain.getLevel())
                .build();
    }
}
