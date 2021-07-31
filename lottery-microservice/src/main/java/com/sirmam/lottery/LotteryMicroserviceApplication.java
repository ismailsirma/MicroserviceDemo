package com.sirmam.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// produce deployment jar
// mvn clean install spring-boot:repackage
// java -jar app.jar
// mvn clean install spring-boot:run
//curl http://localhost:7700/lottery/api/v1/actuator/refresh -X POST -d {} -H "Content-Type: application/json"
@SpringBootApplication
public class LotteryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryMicroserviceApplication.class, args);
	}

}
