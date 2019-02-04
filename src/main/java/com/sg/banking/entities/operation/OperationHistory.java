package com.sg.banking.entities.operation;

import java.time.Instant;

public class OperationHistory {

	private double 		balanceBefore;
	private double 		operationAmount;
	private OperationType 	type;
	private Instant 	date;
	
	public OperationHistory(double balanceBefore,
			double operationAmount,
			OperationType type) {
		this.balanceBefore = balanceBefore;
		this.operationAmount = operationAmount;
		this.type = type;
		this.date = Instant.now();
	}
	public double getBalanceBefore() {
		return balanceBefore;
	}
	public void setBalanceBefore(double balanceBefore) {
		this.balanceBefore = balanceBefore;
	}
	public double getOperationAmount() {
		return operationAmount;
	}
	public void setOperationAmount(double operationAmount) {
		this.operationAmount = operationAmount;
	}
	public OperationType getType() {
		return type;
	}
	public void setType(OperationType type) {
		this.type = type;
	}
	public Instant getDate() {
		return date;
	}
	public void setDate(Instant date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "OperationHistory [balanceBefore=" + balanceBefore + ", operationAmount=" + operationAmount + ", type="
				+ type + ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balanceBefore);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(operationAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OperationHistory other = (OperationHistory) obj;
		if (Double.doubleToLongBits(balanceBefore) != Double.doubleToLongBits(other.balanceBefore))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(operationAmount) != Double.doubleToLongBits(other.operationAmount))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
