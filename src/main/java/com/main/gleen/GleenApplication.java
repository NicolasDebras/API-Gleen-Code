package com.main.gleen;

import com.main.gleen.model.Hero;
import com.main.gleen.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GleenApplication  {

	@Autowired
	HeroRepository test;

	public static void main(String[] args) {
		SpringApplication.run(GleenApplication.class, args);
	}
}
