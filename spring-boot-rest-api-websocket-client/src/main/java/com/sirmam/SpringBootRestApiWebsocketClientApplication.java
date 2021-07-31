package com.sirmam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootRestApiWebsocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiWebsocketClientApplication.class, args);
	}

}
