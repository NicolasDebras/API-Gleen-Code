package mapper;

import com.main.functional.model.Hero;
import dto.AllHeroDto;
import dto.HeroDto;

import java.util.List;

public interface AllHeroMapper {

    static AllHeroDto toDto(List<Hero> heroes) {
        List<HeroDto> heroDto = heroes.stream()
                .map(HeroMapper::toDto)
                .toList();
        return new AllHeroDto(heroDto);

    }


}
