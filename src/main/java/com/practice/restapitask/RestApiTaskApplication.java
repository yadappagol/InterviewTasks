package com.practice.restapitask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rest Api Practice Application", version = "2.0"))
public class RestApiTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTaskApplication.class, args);
	}

}
