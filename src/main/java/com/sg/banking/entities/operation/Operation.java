package com.sg.banking.entities.operation;

import java.time.Instant;

public class Operation {

	private final double 		balanceBefore;
	private final double 		operationAmount;
	private final OperationType	type;
	private final Instant		date;
	
	public Operation(double balanceBefore,
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
	public double getOperationAmount() {
		return operationAmount;
	}	
	public OperationType getType() {
		return type;
	}	
	public Instant getDate() {
		return date;
	}	
	@Override
	public String toString() {
		return "Operation [balanceBefore=" + balanceBefore + ", operationAmount=" + operationAmount + ", type="
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
		Operation other = (Operation) obj;
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
