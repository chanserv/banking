package com.sg.banking.services.account.impl;

import java.util.stream.Collectors;

import com.sg.banking.entities.account.Account;
import com.sg.banking.entities.operation.OperationHistory;
import com.sg.banking.entities.operation.OperationTypeEnum;
import com.sg.banking.exceptions.OverdraftLimitException;
import com.sg.banking.services.account.AccountService;

public class SavingAccountService implements AccountService {
	
	@Override
	public void deposit(Account account, double amount) {
		
		OperationHistory operationHistory = new OperationHistory(account.getBalance(),
				amount, 
				OperationTypeEnum.DEPOSIT);
		account.addHistory(operationHistory);
		
		if(amount < 0) {
			throw new IllegalArgumentException("Negative amount exception '" + amount + "'");
		}
		account.setBalance(account.getBalance() + amount);
	}
	
	@Override
	public void withdrawal(Account account, double amount) {
		
		OperationHistory operationHistory = new OperationHistory(account.getBalance(), 
				amount, 
				OperationTypeEnum.WITHDRAWAL);
		account.addHistory(operationHistory);
		
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
	public String formatString(Account account) {
		return account.getHistory()
			.stream()
			.map(Object::toString)
			.collect(Collectors.joining("\n"));
	}
}
