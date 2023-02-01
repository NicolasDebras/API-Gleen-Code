package com.main.mongodb.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.main.domain.functionnal.model.Rarity;
import com.main.domain.functionnal.model.Speciality;
import com.main.mongodb.entity.HeroEntity;
import com.main.mongodb.migration.utils.EnumRandomizer;
import com.main.mongodb.repository.HeroRepository;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@ChangeLog(order = "001")
public class MigrationHero {

    @ChangeSet(order = "001", id = "addHero", author = "aristide")
    public void initHero(HeroRepository heroRepository) {
        heroRepository.saveAll(createArrayOfHero());
    }


    private List<HeroEntity> createArrayOfHero() {
        val heroEntityRandom = new ArrayList<HeroEntity>();
        Random rand = new Random();
        int randomLevel = rand.nextInt(500) + 200;
        for (int i = 0; i < randomLevel; i++) {
            heroEntityRandom.add(createHero());
        }
        return heroEntityRandom;
    }

    private String createRandomName(int size) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < size; i++) {
            name.append((char) (Math.random() * 26 + 'a'));
        }
        return name.toString();
    }

    private Rarity createRandomRarity() {
        val random = new EnumRandomizer<Rarity>();
        return random.randomValue(Rarity.class);
    }

    private Speciality createRandomSpeciality() {
        val random = new EnumRandomizer<Speciality>();
        return random.randomValue(Speciality.class);
    }


    private HeroEntity createHero() {
        val rarity = createRandomRarity();
        val speciality = createRandomSpeciality();
        return HeroEntity.builder()
                .id(UUID.randomUUID())
                .armor(speciality.getAmor(rarity))
                .experiencePoint(0)
                .name(createRandomName(10))
                .power(speciality.getPower(rarity))
                .Pv(speciality.getPv(rarity))
                .rarity(rarity)
                .speciality(speciality)
                .xp(0)
                .build();
    }
}
