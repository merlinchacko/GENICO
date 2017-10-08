package com.assignment.transferservice.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;

import com.assignment.transferservice.dto.Account;

/**
 * @author Merlin
 *
 */
public class AccountFileWriter {

	private static final String COMMA_DELIMITER = ",";
	private static final CharSequence NEW_LINE_SEPARATOR = "\n";

	/**
	 * @param resource
	 * @param account
	 * @throws Exception
	 * writes to csv file using FileWriter
	 */
	public static void writeToCsv(Resource resource, Account account) throws Exception {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(resource.getFile(), true);
				
			fileWriter.append(account.getAccount_name().toLowerCase());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(String.valueOf(account.getAccount_balance()));
			fileWriter.append(NEW_LINE_SEPARATOR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
	}
	
	/**
	 * @param resource
	 * @param accountList
	 * @throws Exception
	 * writes to csv file using FileWriter
	 */
	public static void writeToCsv(Resource resource, List<Account> accountList) throws Exception {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(resource.getFile(), true);
			
			for(Account account : accountList) {
				
				fileWriter.append(account.getAccount_name().toLowerCase());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(account.getAccount_balance()));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
	}
}


