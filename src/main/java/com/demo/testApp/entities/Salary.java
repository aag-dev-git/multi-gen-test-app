package com.demo.testApp.entities;

import jakarta.persistence.Entity;

@Entity
public class Salary extends BaseEntity {

	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Salary [amount=" + amount + "]";
	}

}
