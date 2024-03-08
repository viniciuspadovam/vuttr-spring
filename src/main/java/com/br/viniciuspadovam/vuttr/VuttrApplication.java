package com.br.viniciuspadovam.vuttr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class VuttrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuttrApplication.class, args);
	}

}
