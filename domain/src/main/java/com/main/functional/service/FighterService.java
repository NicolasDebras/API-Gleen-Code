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
    public Either<ApplicationError, Fighter> fightCard(Fighter fighter) {
        val defenseCard = findCardByUUID(fighter.getDefenseCard().getId());
        val attackCard = findCardByUUID(fighter.getAttackCard().getId());
        if (defenseCard.isLeft() || attackCard.isLeft()) {
            return Either.left(defenseCard.getLeft());
        }
        if (!isPossibleToFight(defenseCard.get(), attackCard.get())) {
            return Either.left(new ApplicationError("The defense card is not possible to fight", null, defenseCard.get().getId(), null));
        }
        val winner = fight(defenseCard.get(), attackCard.get());
        if (winner.isEmpty()) {
            return Either.left(new ApplicationError("The fight is a draw", null, null, null));
        }
        val winnerCardUpdated = updateCardWining(winner.get().equals(defenseCard.get().getId()) ? defenseCard.get() : attackCard.get());
        if (winnerCardUpdated.isLeft()) {
            return Either.left(winnerCardUpdated.getLeft());
        }

        return Either.right(Fighter.builder()
                .attackCard(attackCard.get())
                .defenseCard(defenseCard.get())
                .build());
    }

    private Either<ApplicationError, Card> findCardByUUID(UUID idCard) {
        return cardPersistenceSpi.findById(idCard)
                .map(Either::<ApplicationError, Card>right).getOrElse(Either.left(new ApplicationError("Not possible to find card", null, idCard, null)));
    }

    private Option<UUID> fight(Card defenseCard, Card attackCard) {
        double defenseCardHealth = calculateHealth(defenseCard);
        val defenseCardAttack = calculateAttack(defenseCard, attackCard.getHeroType().getName());
        val attackCardAttack = calculateAttack(attackCard, defenseCard.getHeroType().getName());
        double attackCardHealth = calculateHealth(attackCard);
        while (defenseCardHealth > 0 && attackCardHealth > 0) {
            defenseCardHealth -= attackCardAttack;
            if (defenseCardHealth >= 0) {
                log.info("The winner is {}", attackCard.getHeroType().getName());
                val resultFight = saveFight(attackCard.getId(), defenseCard.getId());
                if (resultFight.isLeft()) {
                    log.error("Not possible to save fight", resultFight.getLeft());
                    return Option.none();
                }
                return Option.of(attackCard.getId());
            }
            attackCardHealth -= defenseCardAttack;
            if (attackCardHealth >= 0) {
                log.info("The winner is {}", defenseCard.getHeroType().getName());
                val resultFight = saveFight(defenseCard.getId() , attackCard.getId());
                if (resultFight.isLeft()) {
                    log.error("Not possible to save fight", resultFight.getLeft());
                    return Option.none();
                }
                return Option.of(defenseCard.getId());
            }
        }
        return Option.none();
    }



    private Boolean isPossibleToFight(Card defenseCard, Card attackCard) {
        return defenseCard.getLevel().getLevel() >= attackCard.getLevel().getLevel();
    }

    private Either<ApplicationError, Card> updateCardWining(Card card) {
        if (card.getExperience() < 5) {
            return cardPersistenceSpi.updateExperience(card);
        } else {
            val updateUserToken = userAccountPersistenceSpi.updateToken(card.getId());
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

    private Double calculateHealth(Card card) {
        val defenseBase = card.getHeroType().getSpeciality().getHealth();
        val percentageLevel = card.getLevel().getExp();
        val rarityPercentage = card.getHeroType().getRarity().getPercentage();
        return defenseBase + (defenseBase * percentageLevel) + (defenseBase * rarityPercentage);
    }

    private Either<ApplicationError, HistoricalFight> saveFight(UUID idWinner, UUID idLoser) {
        return historicalFightPersistenceSpi.save(
                HistoricalFight.builder()
                        .winner(idWinner)
                        .loser(idLoser)
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
