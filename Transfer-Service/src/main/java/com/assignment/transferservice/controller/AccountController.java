package com.assignment.transferservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.transferservice.common.AccountFileReader;
import com.assignment.transferservice.common.AccountFileWriter;
import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.AccountService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		
		try {
			
			accountService.createAccount(account);
		} catch (Exception e) {
			
			return new ResponseEntity<>(gson.toJson("Account creation failed due to server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(gson.toJson("Successfully created."), HttpStatus.OK);
	}
	
	
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
