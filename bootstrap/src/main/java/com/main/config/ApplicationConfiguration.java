package com.main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().registerModule(new VavrModule());
  }
}

