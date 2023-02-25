package config.domain;

import adapter.*;
import com.main.functional.service.*;
import com.main.ports.client.*;
import com.main.ports.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.*;

@Configuration
public class DomainConfiguration {

    @Bean
    public UserAccountPersistenceSpi userAccountPersistenceSpi(UserRepository userRepository) {
        return new UserAccountDatabaseAdapter(userRepository);
    }

    //@Bean
    //public CardPersistenceSpi cardPersistenceSpi(CardRepository cardRepository) {
    //    return new CardDatabaseAdapter(cardRepository);
    //}

    @Bean
    public HistoricalFightPersistenceSpi historicalFightPersistenceSpi(HistoricalFightRepository historicalFightRepository) {
        return new HistoricalFightPersistenceAdapter(historicalFightRepository);
    }

    @Bean
    public HeroPersistenceSpi heroPersistenceSpi(HeroRepository heroRepository) {
        return new HeroDatabaseAdapter(heroRepository);
    }

    @Bean
    public DeckPersistenceSpi deckPersistenceSpi(CardRepository cardRepository) {
        return new DeckDatabaseAdapter(cardRepository);
    }

    @Bean
    public LevelPersistenceSpi levelPersistenceSpi(LevelRepository levelRepository) {
        return new LevelDatabaseAdapter(levelRepository);
    }

    @Bean
    public RarityPersistenceSpi rarityPersistenceSpi(RarityRepository rarityRepository) {
        return new RarityDatabaseAdapter(rarityRepository);
    }

    @Bean
    public SpecialityPersistenceSpi specialityPersistenceSpi(SpecialityRepository specialityRepository) {
        return new SpecialityDatabaseAdapter(specialityRepository);
    }

    @Bean
    public SettingDeckPersistenceSpi settingDeckPersistenceSpi(SettingDeckRepository settingDeckRepository) {
        return new SettingDeckDatabaseAdapter(settingDeckRepository);
    }



    @Bean
    public DrawHeroService drawHeroService(HeroPersistenceSpi heroPersistenceSpi, LevelPersistenceSpi levelPersistenceSpi) {
        return new DrawHeroService(heroPersistenceSpi, levelPersistenceSpi);
    }

    @Bean
    public DeckOpenApi deckOpenApi(UserAccountPersistenceSpi userAccountPersistenceSpi,
                                   DeckPersistenceSpi deckPersistenceSpi,
                                   SettingDeckPersistenceSpi settingDeckPersistenceSpi,
                                   DrawHeroService drawHeroService) {
        return new DeckOpenService(userAccountPersistenceSpi,settingDeckPersistenceSpi,  drawHeroService, deckPersistenceSpi);
    }

    @Bean
    public FightCardApi fightCardApi(CardPersistenceSpi cardPersistenceSpi,
                                     UserAccountPersistenceSpi userAccountPersistenceSpi,
                                     HistoricalFightPersistenceSpi historicalFightPersistenceSpi) {
        return new FighterService(cardPersistenceSpi, userAccountPersistenceSpi, historicalFightPersistenceSpi);
    }

    @Bean
    public FindDeckUserApi findDeckUserApi(UserAccountPersistenceSpi userAccountPersistenceSpi,
                                           DeckPersistenceSpi deckPersistenceSpi) {
        return new FindDeckByUserService(userAccountPersistenceSpi, deckPersistenceSpi);
    }

    @Bean
    public HeroCreatorApi heroCreatorApi(HeroPersistenceSpi heroPersistenceSpi,
                                         RarityPersistenceSpi rarityPersistenceSpi,
                                         SpecialityPersistenceSpi specialityPersistenceSpi) {
        return new HeroCreatorService(heroPersistenceSpi, rarityPersistenceSpi, specialityPersistenceSpi);
    }

    @Bean
    public FindHistoricalFightCardApi findHistoricalFightCardApi(HistoricalFightPersistenceSpi historicalFightPersistenceSpi,
                                                                 CardPersistenceSpi cardPersistenceSpi) {
        return new HistoricalFightService(historicalFightPersistenceSpi, cardPersistenceSpi);
    }

    @Bean
    public ListHeroApi listHeroApi(HeroPersistenceSpi heroPersistenceSpi) {
        return new ListHeroService(heroPersistenceSpi);
    }

    @Bean
    public UserAccountCreatorApi userAccountCreatorApi(UserAccountPersistenceSpi userAccountPersistenceSpi) {
        return new UserAccountCreatorService(userAccountPersistenceSpi);
    }
}
