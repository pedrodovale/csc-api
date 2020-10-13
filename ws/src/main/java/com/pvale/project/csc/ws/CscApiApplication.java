package com.pvale.project.csc.ws;

import com.pvale.project.csc.ws.config.WsMainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WsMainConfig.class)
public class CscApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscApiApplication.class, args);
	}

}
