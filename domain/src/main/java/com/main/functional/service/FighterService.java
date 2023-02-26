package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.AdvantageOtherHero;
import com.main.functional.model.Card;
import com.main.functional.model.Fighter;
import com.main.functional.model.HistoricalFight;
import com.main.ports.client.FightCardApi;
import com.main.ports.server.CardPersistenceSpi;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FighterService implements FightCardApi {

    private final CardPersistenceSpi cardPersistenceSpi;

    private final UserAccountPersistenceSpi userAccountPersistenceSpi;

    private final HistoricalFightPersistenceSpi historicalFightPersistenceSpi;

    @Override
    public Either<ApplicationError, Card> fightCard(Fighter fighter) {
        val defenseCard = findCardByUUID(fighter.getDefenseCard().getId());
        val attackCard = findCardByUUID(fighter.getAttackCard().getId());
        if (defenseCard.isLeft()) {
            return Either.left(defenseCard.getLeft());
        }
        if (attackCard.isLeft()) {
            return Either.left(attackCard.getLeft());
        }
        if (!isPossibleToFight(defenseCard.get(), attackCard.get())) {
            return Either.left(new ApplicationError("The  defense card is not possible to fight", null, defenseCard.get().getId(), null));
        }
        val winner = fight(defenseCard.get(), attackCard.get());
        if (winner.isEmpty()) {
            return Either.left(new ApplicationError("The fight is a draw error", null, null, null));
        }
        val winnerCardUpdated = updateCardWining(winner.get().equals(defenseCard.get().getId()) ? defenseCard.get() : attackCard.get());
        if (winnerCardUpdated.isLeft()) {
            return Either.left(winnerCardUpdated.getLeft());
        }
        return Either.right(winnerCardUpdated.get());
    }

    private Either<ApplicationError, Card> findCardByUUID(UUID idCard) {
        return cardPersistenceSpi.findById(idCard)
                .map(Either::<ApplicationError, Card>right).getOrElse(Either.left(new ApplicationError("Not possible to find card", null, idCard, null)));
    }

    private Option<UUID> fight(Card defenseCard, Card attackCard) {
        double defenseCardHealth = calculateHealth(defenseCard);
        val defenseCardAttack = calculateAttack(defenseCard, attackCard.getHeroType().getName());
        val attackCardAttack = calculateArmor(attackCard);
        val defenseCardArmor = calculateArmor(defenseCard);
        double attackCardHealth = calculateHealth(attackCard);
        val attackCardArmor = calculateArmor(attackCard);
        val attackCardDefense = calculateAttack(attackCard, defenseCard.getHeroType().getName());
        val damageDefenseCard = calculateDamage(attackCardAttack, defenseCardArmor);
        val damageAttackCard = calculateDamage(attackCardDefense,  attackCardArmor);
        while (defenseCardHealth > 0 && attackCardHealth > 0) {
            defenseCardHealth = defenseCardHealth - damageDefenseCard;
            attackCardHealth = attackCardHealth - damageAttackCard;
        }
        if (defenseCardHealth > attackCardHealth) {
            log.info("The winner is the defense card");
            val save = saveFight(attackCard, defenseCard);
            if (save.isLeft()) {
                return Option.none();
            }
            return Option.of(defenseCard.getId());
        }else if (attackCardHealth > defenseCardHealth) {
            log.info("The winner is the attack card");
            val save= saveFight(defenseCard, attackCard);
            if (save.isLeft()) {
                return Option.none();
            }
            return Option.of(attackCard.getId());
        }
        return Option.none();
    }

    private int calculateDamage(Double attack, Double armor) {
        return (int) (attack - armor);
    }



    private Boolean isPossibleToFight(Card defenseCard, Card attackCard) {
        if (defenseCard.getId() == attackCard.getId()) {
            log.info("The cards are the same");
            return false;
        }
        if (defenseCard.getUser().getId() == attackCard.getUser().getId()) {
            log.info("The cards are from the same user");
            return false;
        }
        return defenseCard.getLevel().getLevel() >= attackCard.getLevel().getLevel();
    }

    private Either<ApplicationError, Card> updateCardWining(Card card) {
        if (card.getExperience() < 4) {
            return cardPersistenceSpi.updateExperience(card);
        } else {
            val updateUserToken = userAccountPersistenceSpi.updateToken(card.getUser());
            if (updateUserToken.isLeft()) {
                return Either.left(updateUserToken.getLeft());
            }
            return cardPersistenceSpi.updateLevel(card);
        }
    }


    private Double calculateAttack(Card card, String nameOpponent) {
        val attackBase = card.getHeroType().getSpeciality().getPower();
        val percentageLevel = card.getLevel().getExp();
        val rarityPercentage = card.getHeroType().getRarity().getPercentage();
        val resultAttack = attackBase + (attackBase * percentageLevel) + (attackBase * rarityPercentage);
        val advantage = isAdvantageToOpponent(card.getHeroType().getSpeciality().getAdvantageOtherHeroes(), nameOpponent);
        return resultAttack + (resultAttack * advantage);
    }

    private Double calculateArmor(Card card) {
        val armorBase = card.getHeroType().getSpeciality().getArmor();
        val percentageLevel = card.getLevel().getExp();
        val rarityPercentage = card.getHeroType().getRarity().getPercentage();
        return armorBase + (armorBase * percentageLevel) + (armorBase * rarityPercentage);
    }

    private Double calculateHealth(Card card) {
        val defenseBase = card.getHeroType().getSpeciality().getHealth();
        val percentageLevel = card.getLevel().getExp();
        val rarityPercentage = card.getHeroType().getRarity().getPercentage();
        return defenseBase + (defenseBase * percentageLevel) + (defenseBase * rarityPercentage);
    }

    private Either<ApplicationError, HistoricalFight> saveFight(Card winner, Card loser) {
        return historicalFightPersistenceSpi.save(
                HistoricalFight.builder()
                        .winner(winner)
                        .loser(loser)
                        .build()
                )
                .map(Either::<ApplicationError, HistoricalFight>right).getOrElse(Either.left(new ApplicationError("Not possible to save fight", null, null, null)));
    }


    private Integer isAdvantageToOpponent(List<AdvantageOtherHero> advantageOtherHeroes, String nameOpponent) {
        return advantageOtherHeroes.stream()
                .filter(advantageOtherHero -> advantageOtherHero.getName().equals(nameOpponent))
                .map(AdvantageOtherHero::getPower)
                .findFirst()
                .orElse(0);
    }
}
