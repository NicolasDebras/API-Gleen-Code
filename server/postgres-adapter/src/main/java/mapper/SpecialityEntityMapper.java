package mapper;

import com.main.functional.model.AdvantageOtherHero;
import com.main.functional.model.Speciality;
import entity.AdvantageOtherHeroEntity;
import entity.SpecialityEntity;

import java.util.List;
import java.util.UUID;

public interface SpecialityEntityMapper {

    static Speciality toDomain(SpecialityEntity entity) {
        return Speciality.builder()
                .name(entity.getName())
                .armor(entity.getArmor())
                .power(entity.getPower())
                .advantageOtherHeroes(List.of(entity.getAdvantageOtherHero().stream().map(AdvantageOtherHeroMapper::toDomain).toArray(AdvantageOtherHero[]::new)))
                .health(entity.getHealth())
                .build();
    }

    static SpecialityEntity fromDomain(Speciality domain) {
        return SpecialityEntity.builder()
                .id(UUID.randomUUID())
                .name(domain.getName())
                .armor(domain.getArmor())
                .power(domain.getPower())
                .advantageOtherHero(List.of(domain.getAdvantageOtherHeroes().stream().map(AdvantageOtherHeroMapper::fromDomain).toArray(AdvantageOtherHeroEntity[]::new)))
                .build();
    }
}
