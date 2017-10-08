package com.assignment.transferservice.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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

	@Value("classpath:files/account.csv") private Resource accountFile;
	
	/* (non-Javadoc)
	 * @see com.assignment.transferservice.service.AccountService#getAllAccounts()
	 * Fetch all the account details in the account.csv file
	 */
	@Override
	public List<Account> getAllAccounts() throws Exception {

		List<Account> accountList = new ArrayList<Account>(readDataFromCsv().values());

		return accountList;
	}

	/* (non-Javadoc)
	 * @see com.assignment.transferservice.service.AccountService#createAccount(com.assignment.transferservice.dto.Account)
	 * Create new account in account.csv file. if account name is already present , will return a message as "AlreadyExist"
	 * else as "Created"
	 */
	@Override
	@Async
	public Future<String> createAccount(Account account) throws Exception {
		
		String result = "";

		Map<String, Account> accounts = readDataFromCsv();
		
		if(accounts.containsKey(account.getAccount_name())) {
			
			result = "AlreadyExist";
		} else {
			
			AccountFileWriter.writeToCsv(accountFile , account);
			result = "Created";
		}

		return new AsyncResult<String>(result);
		
	}

	/* (non-Javadoc)
	 * @see com.assignment.transferservice.service.AccountService#transferAccount(com.assignment.transferservice.dto.TransferDetails)
	 * Transfer a particular amount from one account to another\
	 * Return result message as "SameAccount" if from and to account is same.
	 * Return result message as "InsufficientBalance" if transfer amount is not present in from account.
	 * Return result message as "Invalid" if from and to account is not present in account.csv.
	 * Return result message as "Success" if transfer was complete.
	 */
	@Override
	@Async
	public Future<String> transferAccount(TransferDetails details) throws Exception {

		String result = "";

		Map<String, Account> accounts = readDataFromCsv();

		String fromAccount = details.getFromAccount().toLowerCase();
		String toAccount = details.getToAccount().toLowerCase();
		double transferAmount = details.getTransferAmount();
		if(fromAccount.equalsIgnoreCase(toAccount)) {
			
			result = "SameAccount";
		} else {
			if(accounts.containsKey(fromAccount) && accounts.containsKey(toAccount)){
	
				Account from = accounts.get(fromAccount);
				Account to = accounts.get(toAccount);
	
				double difference = from.getAccount_balance() - transferAmount;
	
				if(difference >= 0) {
					from.setAccount_balance(difference);
					to.setAccount_balance(to.getAccount_balance() + transferAmount);
					
					List<Account> accountList = new ArrayList<Account>(accounts.values());
	
					AccountFileWriter.writeToCsv(accountFile, accountList);
					
					result = "Success";
				} else {
					result = "InsufficientBalance";
				}
			} else {
				result = "Invalid";
			}
		}
		
		Thread.sleep(1000L);
		return new AsyncResult<String>(result);
	}

	/**
	 * @return
	 * @throws Exception
	 * method used to read data from account.csv and store to a hashmap
	 * Account name is key(unique) and account object as value
	 */
	private Map<String, Account> readDataFromCsv() throws Exception {

		Map<String, Account> accountsmap = AccountFileReader.readFromCsv(accountFile);

		return accountsmap;
	}


}
