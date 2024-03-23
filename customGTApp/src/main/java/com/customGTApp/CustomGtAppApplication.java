package com.customGTApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com/customGTApp/model"})  // force scan JPA entities
public class CustomGtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomGtAppApplication.class, args);
	}

}
