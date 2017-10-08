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



	private static final String FILE_HEADER = "account_name,account_balance";
	private static final String COMMA_DELIMITER = ",";
	private static final CharSequence NEW_LINE_SEPARATOR = "\n";

	public static void writeToCsv(Resource resource, Account account) throws Exception {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(resource.getFile(), true);
				
			fileWriter.append(account.getAccount_name().toLowerCase());
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(String.valueOf(account.getAccount_balance()));
			fileWriter.append(NEW_LINE_SEPARATOR);
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
				throw new Exception();
			}
		}
	}
	
	public static void writeToCsv(Resource resource, List<Account> accountList) throws Exception {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(resource.getFile(), true);
			
			//fileWriter.append(FILE_HEADER.toString());
			//fileWriter.append(NEW_LINE_SEPARATOR);
			for(Account account : accountList) {
				
				fileWriter.append(account.getAccount_name().toLowerCase());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(account.getAccount_balance()));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			throw new Exception();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
				throw new Exception();
			}
		}
	}
}


