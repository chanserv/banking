package com.sg.banking.service.account.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sg.banking.entities.account.Account;
import com.sg.banking.entities.operation.OperationType;
import com.sg.banking.exceptions.OverdraftLimitException;
import com.sg.banking.services.account.AccountService;
import com.sg.banking.services.account.impl.SavingAccountService;

public class SavingAccountServiceTest {
	
	Account account;
	AccountService accountservice;

	@Before
	public void init() {
		account = new Account();
		accountservice = new SavingAccountService();
	}

	@Test
	public void whenNewAccountCreatedIdIsAutomaticallyGenerated() {
		assertThat(account.getId()).isNotNull();
	}
	
	@Test
	public void when100DepositIsDoneAccountBalanceIncreaseBy100() {

		double amount = 100;
		double oldBalance = account.getBalance();
		accountservice.deposit(account, amount);
		assertThat(account.getBalance()).isEqualTo(oldBalance + amount);
	}
	
	@Test
	public void when150WithdrawalIsDoneAccountBalanceDecreaseBy150() {

		double originalAmmount = 200;
		accountservice.deposit(account, originalAmmount);
		
		double oldBalance = account.getBalance();
		
		double amount = 150;
		accountservice.withdrawal(account, amount);
		assertThat(account.getBalance()).isEqualTo(oldBalance - amount);
	}

	@Test(expected=IllegalArgumentException.class)
	public void cantDepositNegativeAmount() {
		double amount = -100;
		accountservice.deposit(account, amount);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cantWithdrawNegativeAmount() {
		double amount = -100;
		accountservice.withdrawal(account, amount);
	}
	
	@Test(expected=OverdraftLimitException.class)
	public void withdrawalCantExceedTheOverdraftLimit() {
		double amount = 100;
		accountservice.withdrawal(account, amount);
	}
	
	@Test
	public void whenDepositIsDoneACorrespondingOperationShouldBeAddedToHistory() {

		double amount = 150;
		accountservice.deposit(account, amount);
		assertThat(account.getHistory().size()).isEqualTo(1);
		assertThat(account.getHistory().get(0).getType()).isEqualTo(OperationType.DEPOSIT);
	}

	@Test
	public void whenWithdrawalIsDoneACorrespondingOperationShouldBeAddedToHistory() {

		double amount = 100;
		try {
			accountservice.withdrawal(account, amount);
		} catch(Exception exception) {
			assertThat(exception).isInstanceOf(OverdraftLimitException.class);
		}
		assertThat(account.getHistory().size()).isEqualTo(1);
		assertThat(account.getHistory().get(0).getType()).isEqualTo(OperationType.WITHDRAWAL);
	}
	
	@Test
	public void when2OperationsAreDoneShouldOutput2Lines() {
		
		double amount = 100;
		accountservice.deposit(account, amount);
		accountservice.deposit(account, amount);
		String history = accountservice.seeHistory(account);
		String [] lines = history.split("\r\n|\r|\n");
		assertThat(lines.length).isEqualTo(2);
	}

}
