package com.pvale.project.csc.ws;

import com.pvale.project.csc.ws.config.WsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WsConfig.class)
public class CscApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscApiApplication.class, args);
	}

}
