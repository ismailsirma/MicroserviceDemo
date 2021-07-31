package com.sirmam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootRestApiReactiveClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiReactiveClientApplication.class, args);
	}

}
