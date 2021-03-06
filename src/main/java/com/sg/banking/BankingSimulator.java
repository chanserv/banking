package com.sg.banking;

import com.sg.banking.entities.account.Account;
import com.sg.banking.exceptions.OverdraftLimitException;
import com.sg.banking.services.account.AccountService;
import com.sg.banking.services.account.impl.SavingAccountService;

public class BankingSimulator {

	public static void main(String[] args) {
		
		/*
		 * In a real world app, service class (below) will be injected by the container
		 * Or by specific factory method
		 * For the exe purpose, it is instantiated manually
		 */
		AccountService accountService = new SavingAccountService();
		
		Account account = new Account();
		System.out.println(account);
		accountService.deposit(account, 100);
		accountService.deposit(account, 100);
		System.out.println(accountService.seeHistory(account));
		
		System.out.println("----");
		try {
			accountService.withdrawal(account, 300);
		} catch(OverdraftLimitException excetion) {
			excetion.printStackTrace();
		}
		System.out.println(accountService.seeHistory(account));
	}

}
