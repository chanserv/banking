package com.sg.banking.services.account;

import com.sg.banking.entities.account.Account;

public interface AccountService {

	/*
	 * This limit should be placed in the account class as a member
	 * Actually it is placed here for simplicity
	 */
	public static final double OVERDRAFT_LIMIT = 0;

	public void deposit(Account account, double amount);
		
	public void withdrawal(Account account, double amount);
	
	public String formatString(Account account);
}
