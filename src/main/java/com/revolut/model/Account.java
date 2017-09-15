package com.revolut.model;

import java.util.Random;

public class Account {

	private int accountId;
	
	public int getAccountId() {
		return accountId;
	}

	private String accountName;
	
	private double accountBalance;
	
	public Account() {
		Random rand = new Random();
		accountId = rand.nextInt(100);
		
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	
}
