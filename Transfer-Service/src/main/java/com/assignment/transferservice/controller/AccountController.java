package com.assignment.transferservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.AccountService;
import com.google.gson.Gson;

/**
 * @author Merlin
 *
 */
@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	private static final Gson gson = new Gson();
	
	/**
	 * @param Account
	 * @return ResponseEntity<String>
	 * rest service for creating account
	 */
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		
		String resultMsg = "";
		try {
			
			resultMsg = accountService.createAccount(account).get().toString();
		} catch (Exception e) {
			
			return new ResponseEntity<>(gson.toJson("Account creation failed due to server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(gson.toJson(resultMsg), HttpStatus.OK);
	}
	
	/**
	 * @param TransferDetails
	 * @return ResponseEntity<String>
	 * rest service for transfer amount to account
	 */
	@RequestMapping(value = "/transferAccount", method = RequestMethod.POST)
	public ResponseEntity<String> transferAccount(@RequestBody TransferDetails details) {
		
		String resultMsg = "";
		try {
			
			resultMsg = accountService.transferAccount(details).get().toString();
		} catch (Exception e) {
			
			return new ResponseEntity<>(gson.toJson("Amount transfer failed due to server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(gson.toJson(resultMsg), HttpStatus.OK);
	}
	
	/**
	 * @return ResponseEntity<List<Account>>
	 * rest service for listing all accounts
	 */
	@RequestMapping(value = "/getAllAccounts", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> getAllAccounts() {
		
		List<Account> accountList = new ArrayList<>();
		
		try {
			accountList = accountService.getAllAccounts();
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(accountList.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}

}
