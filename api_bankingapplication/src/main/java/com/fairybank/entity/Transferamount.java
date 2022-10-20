package com.fairybank.entity;

import javax.persistence.Entity;

@Entity
public class Transferamount {
	private String accountType;
	private int accountNo;
	private String accountDeposit;
	private double amount;
	public Transferamount(String accountType, int accountNo, String accountDeposit, double amount) {
		super();
		this.accountType = accountType;
		this.accountNo = accountNo;
		this.accountDeposit = accountDeposit;
		this.amount = amount;
	}
	public Transferamount() {
		
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountDeposit() {
		return accountDeposit;
	}
	public void setAccountDeposit(String accountDeposit) {
		this.accountDeposit = accountDeposit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
