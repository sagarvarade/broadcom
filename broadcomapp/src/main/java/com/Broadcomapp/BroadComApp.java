package com.Broadcomapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class BroadComApp {

	public static void main(String[] args) {
		SpringApplication.run(BroadComApp.class, args);
	}
}
