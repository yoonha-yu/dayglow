package com.example.dayglow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class DayglowApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayglowApplication.class, args);
	}
}
