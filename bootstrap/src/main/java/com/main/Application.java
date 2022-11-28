package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.main")
@EntityScan("com.main")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}