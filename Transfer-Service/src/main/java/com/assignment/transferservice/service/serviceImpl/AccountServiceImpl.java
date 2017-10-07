package com.assignment.transferservice.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.assignment.transferservice.common.AccountFileReader;
import com.assignment.transferservice.common.AccountFileWriter;
import com.assignment.transferservice.dto.Account;
import com.assignment.transferservice.dto.TransferDetails;
import com.assignment.transferservice.service.AccountService;

/**
 * @author Merlin
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Value("classpath:files/accounts.csv") private Resource accountFile;
	
	private final String fileName = "D:\\accounts.csv";	
	

	@Override
	public List<Account> getAllAccounts() throws Exception {

		List<Account> accountList = new ArrayList<Account>(readDataFromCsv().values());

		return accountList;
	}

	@Override
	public void createAccount(Account account) throws Exception {

		AccountFileWriter.writeToCsv(fileName , account);
	}

	@Override
	public String transferAccount(TransferDetails details) throws Exception {

		String result = "Success";

		Map<String, Account> accounts = readDataFromCsv();

		String fromAccount = details.getFromAccount().toLowerCase();
		String toAccount = details.getToAccount().toLowerCase();
		double transferAmount = details.getTransferAmount();

		if(accounts.containsKey(fromAccount) && accounts.containsKey(toAccount)){

			Account from = accounts.get(fromAccount);
			Account to = accounts.get(toAccount);

			double difference = from.getAccount_balance() - transferAmount;

			if(difference >= 0) {
				from.setAccount_balance(difference);
				to.setAccount_balance(to.getAccount_balance() + transferAmount);
				
				List<Account> accountList = new ArrayList<Account>(accounts.values());

				AccountFileWriter.writeToCsv(fileName, accountList);
			} else {
				result = "Error";
			}
		} else {
			result = "Invalid";
		}

		return result;
	}

	private Map<String, Account> readDataFromCsv() throws Exception {

		Map<String, Account> accountsmap = AccountFileReader.readFromCsv(fileName);

		return accountsmap;
	}


}
