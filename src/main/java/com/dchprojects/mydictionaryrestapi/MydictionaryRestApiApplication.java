package com.dchprojects.mydictionaryrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MydictionaryRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydictionaryRestApiApplication.class, args);
	}

}
