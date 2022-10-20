package com.fairybank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Addaccount {
	private String accountType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountNo;
	public Addaccount(String accountType, int accountNo) {
		super();
		this.accountType = accountType;
		this.accountNo = accountNo;
	}
	public Addaccount() {
		
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
	
}
