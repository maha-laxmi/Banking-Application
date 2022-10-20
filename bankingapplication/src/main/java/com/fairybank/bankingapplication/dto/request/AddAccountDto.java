package com.fairybank.bankingapplication.dto.request;



public class AddAccountDto {

	private String accountType;
	private long accountNumber;
	private int loginId;
	private double initialAmount;
	public AddAccountDto(String accountType, int customerDetailId) {
		super();
		this.accountType = accountType;
		this.loginId = customerDetailId;
	}
	public AddAccountDto() {
		
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}
}
