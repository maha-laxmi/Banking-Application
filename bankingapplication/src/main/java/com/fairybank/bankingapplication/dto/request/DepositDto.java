package com.fairybank.bankingapplication.dto.request;





public class DepositDto {
	private double depositAmount;
	private double balance;
	private int accountId;
	private String date;
	private String remark;
//	public DepositDto(double depositAmount,double balance,int accountId,int depositId,String date) {
//		super();
//		
//	}
	public DepositDto() {
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
