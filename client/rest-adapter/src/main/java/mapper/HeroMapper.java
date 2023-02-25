package mapper;

import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import dto.HeroCreationDto;
import dto.HeroDto;

public interface HeroMapper {

    static Hero heroCreationToDomain(HeroCreationDto dto) {
        return Hero.builder()
                .name(dto.name())
                .speciality(Speciality.builder().name(dto.speciality()).build())
                .rarity(Rarity.builder().name(dto.rarity()).build())
                .build();
    }

    static HeroDto toDto(Hero hero) {
        return new HeroDto(
                hero.getId(),
                hero.getName(),
                hero.getSpeciality().getName(),
                hero.getRarity().getName());
    }


}
