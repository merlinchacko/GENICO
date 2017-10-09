package com.assignment.transferservice;

import java.io.File;
import java.io.IOException;

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

		//create a file in user home

		File file = new File(System.getProperty("user.home")+"/account.csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Account>> response =
				restTemplate.exchange("http://localhost:8080/getAllAccounts",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
				});
		 */
	}
}
