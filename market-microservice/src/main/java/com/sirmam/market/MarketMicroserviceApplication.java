package com.sirmam.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketMicroserviceApplication.class, args);
	}

}
