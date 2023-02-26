package com.main.mapper;

import com.main.functional.model.Card;
import com.main.dto.CardDto;
import lombok.val;

public interface CardMapper {

    static CardDto toDto(Card card) {
        val health = card.getHeroType().getSpeciality().getHealth();
        val power = card.getHeroType().getSpeciality().getPower();
        val armor = card.getHeroType().getSpeciality().getArmor();
        val percentageLevel = card.getLevel().getExp();
        val rarityPercentage = card.getHeroType().getRarity().getPercentage();
        return new CardDto(
                card.getId(),
                card.getHeroType().getName(),
                card.getHeroType().getRarity().getName(),
                card.getHeroType().getSpeciality().getName(),
                (int) (health + (health * percentageLevel) + (health * rarityPercentage)),
                (int) (power + (power * percentageLevel) + (power * rarityPercentage)),
                card.getLevel().getLevel(),
                card.getExperience(),
                (int) (armor + (armor * percentageLevel) + (armor * rarityPercentage))
        );
    }
}
