package com.assignment.transferservice;

import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.AccountService;


@SpringBootApplication
@EnableAsync
public class TransferServiceApplication implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(TransferServiceApplication.class);

	@Autowired
	AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(TransferServiceApplication.class, args);
	}

	public void run(String... arg0) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		/*ResponseEntity<List<Account>> response =
				restTemplate.exchange("http://localhost:8080/getAllAccounts",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
				});*/
		long start = System.currentTimeMillis();
		
		TransferDetails details1 = new TransferDetails();

		details1.setFromAccount("Merlin");
		details1.setToAccount("Sunil");
		details1.setTransferAmount(100.0);
		
		TransferDetails details2 = new TransferDetails();

		details2.setFromAccount("sd");
		details2.setToAccount("tessa");
		details2.setTransferAmount(100.0);

		TransferDetails details3 = new TransferDetails();

		details3.setFromAccount("tessa");
		details3.setToAccount("Sunil");
		details3.setTransferAmount(100.0);

		TransferDetails details4 = new TransferDetails();

		details4.setFromAccount("Merlin");
		details4.setToAccount("irene");
		details4.setTransferAmount(100.0);

		TransferDetails details5 = new TransferDetails();

		details5.setFromAccount("abc");
		details5.setToAccount("cds");
		details5.setTransferAmount(100.0);
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<TransferDetails> entity1 = new HttpEntity<TransferDetails>(details1,headers);
		HttpEntity<TransferDetails> entity2 = new HttpEntity<TransferDetails>(details2,headers);
		HttpEntity<TransferDetails> entity3 = new HttpEntity<TransferDetails>(details3,headers);
		HttpEntity<TransferDetails> entity4 = new HttpEntity<TransferDetails>(details4,headers);
		HttpEntity<TransferDetails> entity5 = new HttpEntity<TransferDetails>(details5,headers);

		/*ResponseEntity<String> response1 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity1, String.class);
		ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity2, String.class);
		ResponseEntity<String> response3 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity3, String.class);
		ResponseEntity<String> response4 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity4, String.class);
		ResponseEntity<String> response5 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity5, String.class);
		
		log.info("Time: " + (System.currentTimeMillis() - start));
		log.info("response1---------->"+response1.getBody());
		log.info("response2---------->"+response2.getBody());
		log.info("response3---------->"+response3.getBody());
		log.info("response4---------->"+response4.getBody());
		log.info("response5---------->"+response5.getBody());

		while (!(greet1.isDone() && greet2.isDone() && greet3.isDone()
				&& greet4.isDone() && greet5.isDone())) {
			Thread.sleep(10);
		}*/

	}
}
