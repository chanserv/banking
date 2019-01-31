package com.sg.banking.entities.account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sg.banking.entities.operation.OperationHistory;

public class Account {

	private UUID 					id;
	private double 					balance;
	private List<OperationHistory>	history = new ArrayList<OperationHistory>();

	public Account() {
		this.id = UUID.randomUUID();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<OperationHistory> getHistory() {
		return history;
	}
	public void setHistory(List<OperationHistory> history) {
		this.history = history;
	}
	public void addHistory(OperationHistory operationHistory) {
		history.add(operationHistory);
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", history=" + history + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
