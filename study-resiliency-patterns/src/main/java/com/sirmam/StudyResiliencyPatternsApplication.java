package com.sirmam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sirmam.service.BinanceRestClientPollingService;

@SpringBootApplication
@EnableScheduling
public class StudyResiliencyPatternsApplication implements ApplicationRunner {

	@Autowired
	private BinanceRestClientPollingService srv;
	
	public static void main(String[] args) {
		SpringApplication.run(StudyResiliencyPatternsApplication.class, args);
	}

	// run when spring app right after initialised
	@Override 
	public void run(ApplicationArguments args) throws Exception {
		srv.callBinance();
	}
}
