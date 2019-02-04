package com.sg.banking.services.account.impl;

import java.util.stream.Collectors;

import com.sg.banking.entities.account.Account;
import com.sg.banking.entities.operation.Operation;
import com.sg.banking.entities.operation.OperationType;
import com.sg.banking.exceptions.OverdraftLimitException;
import com.sg.banking.services.account.AccountService;

public class SavingAccountService implements AccountService {
	
	@Override
	public void deposit(Account account, double amount) {
		
		Operation operation = new Operation(account.getBalance(),
				amount, 
				OperationType.DEPOSIT);
		account.addHistory(operation);
		
		if(amount < 0) {
			throw new IllegalArgumentException("Negative amount exception '" + amount + "'");
		}
		account.setBalance(account.getBalance() + amount);
	}
	
	@Override
	public void withdrawal(Account account, double amount) {
		
		Operation operation = new Operation(account.getBalance(), 
				amount, 
				OperationType.WITHDRAWAL);
		account.addHistory(operation);
		
		if(amount < 0) {
			throw new IllegalArgumentException("Negative amount exception");
		}
		
		double newBalance = account.getBalance() - amount;
		if(newBalance < OVERDRAFT_LIMIT) {
			throw new OverdraftLimitException("Overdraft limit exceeded");
		} 
		account.setBalance(account.getBalance() - amount);
	}
	
	/*
	 * Actually this method produce the same output as "toString()"
	 * But we can move all this logic to a dto class top preprocess data 
	 */
	@Override
	public String seeHistory(Account account) {
		return account.getHistory()
			.stream()
			.map(Object::toString)
			.collect(Collectors.joining("\n"));
	}
}
