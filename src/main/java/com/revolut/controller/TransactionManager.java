package com.revolut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revolut.model.Account;

public class TransactionManager {
	
	public TransactionManager() {
		accountsLedger = new HashMap<String, Account>();
	}

	public Map<String,Account> accountsLedger;
	
	public boolean addAccount(Account account) {
		accountsLedger.put(account.getAccountName(), account);
		return true;
	}
	
	public Account getAccount(String accountName) {
		
		return accountsLedger.get(accountName);
	}
	
	public List<Account> getAllAccounts(){
		List<Account> accounts = new ArrayList<Account>(accountsLedger.values());
		return accounts;
	}
}
