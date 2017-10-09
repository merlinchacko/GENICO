package com.assignment.transferservice.service;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.serviceImpl.AccountServiceImpl;
/**
 * @author Merlin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AccountServiceTest {

	@Configuration
	static class AccountServiceTestContextConfiguration {
		@Bean
		public AccountService accountService() {
			return new AccountServiceImpl();
		}
	}
	@Autowired
	AccountService accountService;

	@Test
	public void createAccountSuccessTest() throws Exception {
		
		Random ran = new Random();
		int x = ran.nextInt(10000) + 10;
		
		int beforeSize = accountService.getAllAccounts().size();

		accountService.createAccount(new Account("Account "+x, 100.0));

		int afterSize = accountService.getAllAccounts().size();

		assertNotEquals(beforeSize, afterSize);
	}

	@Test
	public void transferAccountSimultaneousTest() throws Exception {
		
		//RestTemplate restTemplate = new RestTemplate();
		accountService.createAccount(new Account("Account 0", 1000));
		accountService.createAccount(new Account("Account 1", 1000));
		accountService.createAccount(new Account("Account 2", 1000));
		accountService.createAccount(new Account("Account 3", 1000));

		TransferDetails details1 = new TransferDetails();

		details1.setFromAccount("account 1");
		details1.setToAccount("account 2");
		details1.setTransferAmount(100.0);
		
		TransferDetails details2 = new TransferDetails();

		details2.setFromAccount("account 3");
		details2.setToAccount("account 10");
		details2.setTransferAmount(100.0);

		TransferDetails details3 = new TransferDetails();

		details3.setFromAccount("account 2");
		details3.setToAccount("account 3");
		details3.setTransferAmount(10000.0);
		
		TransferDetails details4 = new TransferDetails();

		details4.setFromAccount("account 2");
		details4.setToAccount("account 2");
		details4.setTransferAmount(10000.0);

		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<TransferDetails> entity1 = new HttpEntity<TransferDetails>(details1,headers);
		HttpEntity<TransferDetails> entity2 = new HttpEntity<TransferDetails>(details2,headers);
		HttpEntity<TransferDetails> entity3 = new HttpEntity<TransferDetails>(details3,headers);
		HttpEntity<TransferDetails> entity4 = new HttpEntity<TransferDetails>(details4,headers);

		ResponseEntity<String> response1 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity1, String.class);
		ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity2, String.class);
		ResponseEntity<String> response3 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity3, String.class);
		ResponseEntity<String> response4 = restTemplate.exchange("http://localhost:8080/transferAccount", HttpMethod.POST, entity4, String.class);*/
		
		assertEquals("Success", accountService.transferAccount(details1).get().toString());
		assertEquals("Invalid", accountService.transferAccount(details2).get().toString());
		assertEquals("InsufficientBalance", accountService.transferAccount(details3).get().toString());
		assertEquals("SameAccount", accountService.transferAccount(details4).get().toString());
	}

}
