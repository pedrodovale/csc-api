package com.pvale.project.csc.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pvale.project.csc.ws", "com.pvale.project.csc.api", "com.pvale.project.csc.bsl"})
public class CscApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscApiApplication.class, args);
	}

}