package com.fairybank.bankingapplication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TransactionHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionHistory_id;
	private String date;
	private String description;
	private double credit;
	private double debit;
	private double balance;
	private String remark;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private AddAccountEntity addAccountEntity;

	public TransactionHistoryEntity(int transactionHistory_id, double depositAmount,
			double withdrawAmount, double balance) {
		super();
		this.transactionHistory_id = transactionHistory_id;
		this.credit = depositAmount;
		this.debit = withdrawAmount;
		this.balance = balance;
	}
	public TransactionHistoryEntity() {
		
	}
	public int getTransactionHistory_id() {
		return transactionHistory_id;
	}
	public void setTransactionHistory_id(int transactionHistory_id) {
		this.transactionHistory_id = transactionHistory_id;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddAccountEntity getAddAccountEntity() {
		return addAccountEntity;
	}

	public void setAddAccountEntity(AddAccountEntity addAccountEntity) {
		this.addAccountEntity = addAccountEntity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
