package com.main.config.domain;


import com.main.domain.functionnal.service.HeroCreatorService;
import com.main.domain.ports.client.HeroCreatorApi;
import com.main.domain.ports.server.HeroPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DomainConfiguration {

  @Bean
  public HeroCreatorApi drivingLicenceCreatorService(HeroPersistenceSpi spi) {
    return new HeroCreatorService(spi);
  }


}
