package com.main;

import com.main.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.main")

@EntityScan("com.main")
//@ComponentScan("fr.carbon.it.tech.prez.archi.hexa")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}