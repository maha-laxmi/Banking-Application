package com.fairybank.bankingapplication.dto.request;

public class TransactionEntityDto {
	private int account_id;
	private double depositAmount;
	private double withrawAmount;
	private double balance;

	public TransactionEntityDto( int account_id, double depositAmount, double withrawAmount,
			double balance) {
		super();
		this.account_id = account_id;
		this.depositAmount = depositAmount;
		this.withrawAmount = withrawAmount;
		this.balance = balance;
	}
	public TransactionEntityDto() {
		
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public double getWithrawAmount() {
		return withrawAmount;
	}
	public void setWithrawAmount(double withrawAmount) {
		this.withrawAmount = withrawAmount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
