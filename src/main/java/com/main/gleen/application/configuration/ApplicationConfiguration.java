package com.main.gleen.application.configuration;

import com.main.gleen.application.adapter.HeroServiceAdapter;
import com.main.gleen.application.api.restful.HeroService;
import com.main.gleen.domain.spi.HeroPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public HeroService getHeroService(HeroPersistencePort heroPersistencePort) {
        return new HeroServiceAdapter(heroPersistencePort);
    }
}