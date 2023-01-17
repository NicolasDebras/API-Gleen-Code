package com.main.config.domain;

import com.main.domain.functionnal.service.HeroCreatorService;
import com.main.domain.functionnal.service.UserCreatorService;
import com.main.domain.ports.client.HeroCreatorApi;
import com.main.domain.ports.client.UserCreatorApi;
import com.main.domain.ports.server.HeroPersistenceSpi;
import com.main.domain.ports.server.UserPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public HeroCreatorApi heroCreatorService(HeroPersistenceSpi spi){
        return new HeroCreatorService(spi);
    }


    @Bean
    public UserCreatorApi userCreatorService(UserPersistenceSpi spi){
        return new UserCreatorService(spi);
    }
}
