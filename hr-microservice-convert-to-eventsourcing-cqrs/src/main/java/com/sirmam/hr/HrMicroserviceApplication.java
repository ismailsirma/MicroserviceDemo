package com.sirmam.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// mvn clean package 
// mvn clean install spring-boot:repackage 
// mvn clean install spring-boot:run 
@SpringBootApplication
public class HrMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrMicroserviceApplication.class, args);
	}

}
