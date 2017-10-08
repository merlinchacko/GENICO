package com.assignment.transferservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransferServiceApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

	public void run(String... arg0) throws Exception {
		
		/*RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Account>> response =
				restTemplate.exchange("http://localhost:8080/getAllAccounts",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
				});
		*/
	}
}
