package com.main;

import com.main.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.main")
@EnableJpaRepositories("com.main")
@EntityScan("com.main")
//@ComponentScan("fr.carbon.it.tech.prez.archi.hexa")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}