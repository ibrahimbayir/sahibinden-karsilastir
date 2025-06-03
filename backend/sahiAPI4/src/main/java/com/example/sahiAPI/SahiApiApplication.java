package com.example.sahiAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.sahiAPI")
public class SahiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SahiApiApplication.class, args);
	}

}
