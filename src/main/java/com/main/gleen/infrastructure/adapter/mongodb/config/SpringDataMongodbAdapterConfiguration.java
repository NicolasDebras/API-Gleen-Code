package com.main.gleen.infrastructure.adapter.mongodb.config;

import com.main.gleen.domain.spi.HeroPersistencePort;
import com.main.gleen.infrastructure.adapter.mongodb.adapter.HeroSpringAdapter;
import com.main.gleen.infrastructure.adapter.mongodb.repository.HeroRepository;
import org.springframework.context.annotation.Bean;

public class SpringDataMongodbAdapterConfiguration {
    @Bean
    public HeroPersistencePort getHeroPersistencPort(HeroRepository heroRepository) {
        return new HeroSpringAdapter(heroRepository);
    }
}
