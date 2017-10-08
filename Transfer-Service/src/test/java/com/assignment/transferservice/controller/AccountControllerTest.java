package com.assignment.transferservice.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.AccountService;
import com.google.gson.Gson;

/**
 * @author Merlin
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class, secure = false)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	String exampleAccount = "{\"account_name\":\"Jakes\",\"account_balance\":\"120300\"}";
	
	String exampleTransfer = "{\"fromAccount\":\"Jakes\",\"toAccount\":\"Jax\",\"transferAmount\":\"100\"}";

	private static final Gson gson = new Gson();

	@Test
	//@Ignore
	public void createAccountSuccessTest() throws Exception {
		
		String resultMsg = "Created";
		
		Mockito.when(
				accountService.createAccount(Mockito.any(Account.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createAccount")
				.content(exampleAccount)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void createAccountAlreadyExistTest() throws Exception {
		
		String resultMsg = "AlreadyExist";
		
		Mockito.when(
				accountService.createAccount(Mockito.any(Account.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createAccount")
				.content(exampleAccount)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void createAccountExceptionTest() throws Exception {
		
		String resultMsg = "Account creation failed due to server error!";
		
		Mockito.when(
				accountService.createAccount(Mockito.any(Account.class))).thenThrow(new Exception());

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createAccount")
				.content(exampleAccount)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void transferAccountSuccessTest() throws Exception {
		
		String resultMsg = "Success";
		
		Mockito.when(
				accountService.transferAccount(Mockito.any(TransferDetails.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transferAccount")
				.content(exampleTransfer)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void transferAccountSameAccountTest() throws Exception {
		
		String resultMsg = "SameAccount";
		
		Mockito.when(
				accountService.transferAccount(Mockito.any(TransferDetails.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transferAccount")
				.content(exampleTransfer)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void transferAccountInvalidTest() throws Exception {
		
		String resultMsg = "Invalid";
		
		Mockito.when(
				accountService.transferAccount(Mockito.any(TransferDetails.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transferAccount")
				.content(exampleTransfer)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void transferAccountInsufficientTest() throws Exception {
		
		String resultMsg = "InsufficientBalance";
		
		Mockito.when(
				accountService.transferAccount(Mockito.any(TransferDetails.class))).thenReturn( new AsyncResult<String>(resultMsg));

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transferAccount")
				.content(exampleTransfer)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}
	
	@Test
	//@Ignore
	public void transferAccountExceptionTest() throws Exception {
		
		String resultMsg = "Amount transfer failed due to server error!";
		
		Mockito.when(
				accountService.transferAccount(Mockito.any(TransferDetails.class))).thenThrow(new Exception());

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/transferAccount")
				.content(exampleTransfer)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

		assertEquals(gson.toJson(resultMsg), response.getContentAsString());
	}


}
