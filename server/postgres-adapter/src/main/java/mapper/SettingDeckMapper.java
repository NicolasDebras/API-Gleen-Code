package mapper;

import com.main.functional.model.DeckSetting;

public interface SettingDeckMapper {

    static DeckSetting toDomain(entity.SettingDeckEntity entity) {
        return DeckSetting.builder()
                .nameDeckType(entity.getNameDeckType())
                .token(entity.getToken())
                .numberCard(entity.getNumberCard())
                .build();
    }
}
