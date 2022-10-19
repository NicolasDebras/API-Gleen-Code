package com.main.gleen;

import com.main.gleen.model.Hero;
import com.main.gleen.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class GleenApplication implements CommandLineRunner {

	@Autowired
	ItemRepository test;

	public static void main(String[] args) {
		SpringApplication.run(GleenApplication.class, args);
	}
	void test12() {
		Hero add = Hero.builder()
				.Pv(12)
				.name("test")
				.build();
		test.save(add);
	}
	@Override
	public void run(String... args) throws Exception {
		test12();
	}
}
