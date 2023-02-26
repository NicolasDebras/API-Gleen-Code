package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.main" )
@EnableJpaRepositories(basePackages = "com.main")
@EntityScan("com.main")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
