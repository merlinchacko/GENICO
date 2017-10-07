package com.assignment.transferservice.dto;

/**
 * @author Merlin
 *
 */
public class Account {
	
	private long account_id;
	private String account_name;
	private String account_number;
	private double account_balance;
	
	public Account() {
    }
	
	public Account(String account_name, double account_balance) {

		this.account_name = account_name;
		this.account_balance = account_balance;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public double getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", account_name=" + account_name + ", account_number="
				+ account_number + ", account_balance=" + account_balance + "]";
	}

}
