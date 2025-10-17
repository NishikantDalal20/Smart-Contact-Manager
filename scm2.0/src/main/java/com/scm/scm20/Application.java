package com.scm.scm20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.paulschwarz.springdotenv.DotenvPropertySource;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// DotenvPropertySource.load(); // Load .env
		 
		SpringApplication.run(Application.class, args);
	}

}
