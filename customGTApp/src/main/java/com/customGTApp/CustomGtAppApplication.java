package com.customGTApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Backend application for our website.
 * Basic explanation for working with our tables it's found in the Product classes, as the other classes are implemented
 * the same but with different models. Also, if it's something special implemented it will have an explanation.
 */
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com/customGTApp/model"})  // force scan JPA entities
public class CustomGtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomGtAppApplication.class, args);
	}

}
