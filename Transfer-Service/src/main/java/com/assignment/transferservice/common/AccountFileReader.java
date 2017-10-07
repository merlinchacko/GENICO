package com.assignment.transferservice.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.assignment.transferservice.dto.Account;

/**
 * @author Merlin
 *
 */
public class AccountFileReader {

	private static final String COMMA_DELIMITER = ",";
	private static final int ACCOUNT_NAME_IDX = 0;
    private static final int ACCOUNT_BALANCE_IDX = 1;
	    
	    public static  Map<String, Account>  readFromCsv(String fileName) throws Exception {
	        BufferedReader fileReader = null;
	        Map<String, Account> accountMap = new HashMap<>();
	        try {
	             
	            String line = "";

	            fileReader = new BufferedReader(new FileReader(fileName));
	             
	            fileReader.readLine();
	             
	            while ((line = fileReader.readLine()) != null) {

	            	String[] tokens = line.split(COMMA_DELIMITER);
	                if (tokens.length > 0) {
	                	Account account = new Account(tokens[ACCOUNT_NAME_IDX], Double.parseDouble(tokens[ACCOUNT_BALANCE_IDX]));
	                	accountMap.put(tokens[ACCOUNT_NAME_IDX], account);
	                }
	            }
	        }
	        catch (Exception e) {
	            System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	            throw new Exception();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	                System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	                throw new Exception();
	            }
	        }
			return accountMap;
	    }

}
