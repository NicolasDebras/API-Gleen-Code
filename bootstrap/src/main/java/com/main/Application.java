package com.main;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.main.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import({ApplicationConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.main")
@EnableMongock
@EnableSwagger2
@EntityScan("com.main")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}