package com.assignment.transferservice.service;

import java.util.List;

import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;

/**
 * @author Merlin
 *
 */
public interface AccountService {

	List<Account> getAllAccounts() throws Exception;

	void createAccount(Account account) throws Exception;

	String transferAccount(TransferDetails details) throws Exception;

}
