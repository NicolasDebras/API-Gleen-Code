package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.main")
//@EnableMongoRepositories("com.main")
@EntityScan("com.main")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}