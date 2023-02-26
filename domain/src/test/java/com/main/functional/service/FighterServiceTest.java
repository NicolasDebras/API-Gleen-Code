package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.*;
import com.main.ports.server.CardPersistenceSpi;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FighterServiceTest {

    @Mock
    private CardPersistenceSpi cardPersistenceSpi;

    @Mock
    private UserAccountPersistenceSpi userAccountPersistenceSpi;

    @Mock
    private HistoricalFightPersistenceSpi historicalFightPersistenceSpi;

    @InjectMocks
    private FighterService fighterService;

    @Test
    @DisplayName("should create fight winner is defense Card")
    void should_create_fight() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder()
                        .id(idUserDefense)
                        .build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder()
                        .id(idUserAttack)
                        .build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder()
                        .id(idUserDefense)
                        .build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(cardPersistenceSpi.updateExperience(any())).thenReturn(Either.right(cardWinner));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnRight(cardWinner);
    }

    @Test
    @DisplayName("should not create fight user is same")
    void should_notCreate_fight_sameUser() {
        val idUser = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder()
                        .id(idUser)
                        .build()
                )
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder()
                        .id(idUser)
                        .build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The  defense card is not possible to fight", null, cardDefense.getId(), null));
    }

    @Test
    @DisplayName("should not create fight card is same")
    void should_notCreateFight_sameCard() {
        val idUser = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();

        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUser).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();
        val idAttack = UUID.randomUUID();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUser).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder().id(idDefense).build())
                .attackCard(Card.builder().id(idAttack).build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The  defense card is not possible to fight", null, cardDefense.getId(), null));
    }

    @Test
    @DisplayName("should not find card Attack by id")
    void should_notCreateFight_notFindAttackCard() {
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();

        val idAttack = UUID.randomUUID();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.none());
        val actual = fighterService.fightCard(Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build());
        assertThat(actual).containsOnLeft(new ApplicationError("Not possible to find card", null, idAttack, null));
    }

    @Test
    @DisplayName("should not find card Defense by id")
    void should_notCreateFight_notFindDefenseCard() {
        val idUserAttack = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder().id(idDefense).build())
                .attackCard(Card.builder().id(idAttack).build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.none());
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("Not possible to find card", null, idDefense, null));
    }

    @Test
    @DisplayName("should not create fight card is same")
    void should_notCreateFight_isSameCard() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val level = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val id = UUID.randomUUID();
        val advantage = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val speciality = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantage))
                .build();
        val rarity = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroType = Hero.builder()
                .name("Wizard")
                .speciality(speciality)
                .rarity(rarity)
                .build();
        val cardAttack = Card.builder()
                .id(id)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroType)
                .level(level)
                .experience(0)
                .build();
        val cardDefense = Card.builder()
                .id(id)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroType)
                .level(level)
                .experience(0)
                .build();

        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(id)
                        .build())
                .attackCard(Card.builder()
                        .id(id)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(id)).thenReturn(Option.of(cardAttack));
        when(cardPersistenceSpi.findById(id)).thenReturn(Option.of(cardDefense));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The  defense card is not possible to fight", null, id, null));

    }

    @Test
    @DisplayName("should create fight winner is Attack Card")
    void should_create_fight_AttackWin() {
        val idUserDefense = UUID.randomUUID();
        val idUserAttack = UUID.randomUUID();
        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val levelAttack = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Hero")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelAttack)
                .experience(0)
                .build();

        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Wizard")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelAttack)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelAttack)
                .experience(1)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(cardPersistenceSpi.updateExperience(any())).thenReturn(Either.right(cardWinner));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnRight(cardWinner);
    }

    @Test
    @DisplayName("should create fight but is equal zero Health")
    void should_create_fight_but_zeroHealth(){
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(0)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(0)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The fight is a draw error", null, null, null));
    }


    @Test
    @DisplayName("should create fight winner is Attack Card but no save")
    void should_create_fight_AttackWin_butNoSave() {
        val idUserDefense = UUID.randomUUID();
        val idUserAttack = UUID.randomUUID();
        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val levelAttack = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Hero")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelAttack)
                .experience(0)
                .build();

        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Wizard")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelAttack)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.left(new ApplicationError("Error save", null, null, null)));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The fight is a draw error", null, null, null));
    }

    @Test
    @DisplayName("should create fight winner is defense Card")
    void should_create_fight_DefenseWin_butNoSave() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.left(new ApplicationError("Error save", null, null, null)));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("The fight is a draw error", null, null, null));
    }


    @Test
    @DisplayName("should create fight winner is defense Card with Update Token")
    void should_create_fight_withUpdateToken() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(4)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val cardWinnerUpdate = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(Level.builder()
                        .level(2)
                        .exp(0.10)
                        .build())
                .experience(1)
                .build();
        val cardResetExperience = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(Level.builder()
                        .level(2)
                        .exp(0.10)
                        .build())
                .experience(0)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(userAccountPersistenceSpi.updateToken(any())).thenReturn(Either.right(User.builder()
                .id(idUserDefense)
                .token(2)
                .build()));
        when(cardPersistenceSpi.updateLevel(any())).thenReturn(Either.right(cardWinnerUpdate));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        when(cardPersistenceSpi.resetExperience(any())).thenReturn(Either.right(cardResetExperience));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnRight(cardWinnerUpdate);
    }

    @Test
    @DisplayName("should create fight winner is defense Card with Update Level Error")
    void should_create_fight_withUpdateLevel_error() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(4)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        val cardResetExperience = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(0)
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(cardPersistenceSpi.updateLevel(any())).thenReturn(Either.left(new ApplicationError("Error save Update Level", null, null, null)));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        when( userAccountPersistenceSpi.updateToken(any())).thenReturn(Either.right(User.builder()
                .id(idUserDefense)
                .token(0)
                .build()));

        when(cardPersistenceSpi.resetExperience(any())).thenReturn(Either.right(cardResetExperience));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("Error save Update Level", null, null, null));
    }

    @Test
    @DisplayName("should create fight winner is defense Card with Update Token Error")
    void should_create_fight_withUpdateToken_error() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(4)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(userAccountPersistenceSpi.updateToken(User.builder().id(idUserDefense).build())).thenReturn(Either.left(new ApplicationError("Error save Update Token", null, null, null)));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("Error save Update Token", null, null, null));
    }

    @Test
    @DisplayName("should create fight winner is defense Card with Max Level")
    void should_create_fight_withMaxLevel() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(100)
                .exp(10.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(4)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val cardWinnerUpdate = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(Level.builder()
                        .level(100)
                        .exp(10.00)
                        .build())
                .experience(4)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(userAccountPersistenceSpi.updateToken(any())).thenReturn(Either.right(User.builder()
                .id(idUserDefense)
                .token(2)
                .build()));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnRight(cardWinnerUpdate);
    }

    @Test
    @DisplayName("reset Experience don't work")
    void should_create_fight_Experience_dontWork() {
        val idUserAttack = UUID.randomUUID();
        val idUserDefense = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val advantageDefense = AdvantageOtherHero.builder()
                .name("Wizard")
                .build();
        val specialityDefense = Speciality.builder()
                .name("Tank")
                .armor(1000)
                .health(100)
                .power(20)
                .advantageOtherHeroes(List.of(advantageDefense))
                .build();
        val levelDefense = Level.builder()
                .level(1)
                .exp(0.00)
                .build();
        val rarityDefense = Rarity.builder()
                .name("Common")
                .percentage(0.00)
                .build();
        val heroTypeDefense = Hero.builder()
                .name("Hero")
                .speciality(specialityDefense)
                .rarity(rarityDefense)
                .build();
        val cardDefense = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(4)
                .build();

        val idAttack = UUID.randomUUID();
        val advantageAttack = AdvantageOtherHero.builder()
                .name("Murder")
                .build();
        val specialityAttack = Speciality.builder()
                .name("Wizard")
                .armor(10)
                .health(700)
                .power(200)
                .advantageOtherHeroes(List.of(advantageAttack))
                .build();
        val rarityAttack = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val heroTypeAttack = Hero.builder()
                .name("Wizard")
                .speciality(specialityAttack)
                .rarity(rarityAttack)
                .build();
        val cardAttack = Card.builder()
                .id(idAttack)
                .user(User.builder().id(idUserAttack).build())
                .heroType(heroTypeAttack)
                .level(levelDefense)
                .experience(0)
                .build();
        val cardWinner = Card.builder()
                .id(idDefense)
                .user(User.builder().id(idUserDefense).build())
                .heroType(heroTypeDefense)
                .level(levelDefense)
                .experience(1)
                .build();
        val fight = Fighter.builder()
                .defenseCard(Card.builder()
                        .id(idDefense)
                        .build())
                .attackCard(Card.builder()
                        .id(idAttack)
                        .build())
                .build();
        when(cardPersistenceSpi.findById(eq(idDefense))).thenReturn(Option.of(cardDefense));
        when(userAccountPersistenceSpi.updateToken(any())).thenReturn(Either.right(User.builder()
                .id(idUserDefense)
                .token(2)
                .build()));
        when(cardPersistenceSpi.findById(eq(idAttack))).thenReturn(Option.of(cardAttack));
        when(historicalFightPersistenceSpi.save(any())).thenReturn(Either.right(HistoricalFight.builder()
                .winner(cardWinner)
                .loser(cardAttack)
                .build()));
        when(cardPersistenceSpi.resetExperience(any())).thenReturn(Either.left(new ApplicationError("Error save Reset Experience", null, null, null)));
        val actual = fighterService.fightCard(fight);
        assertThat(actual).containsOnLeft(new ApplicationError("Error save Reset Experience", null, null, null));
    }

}