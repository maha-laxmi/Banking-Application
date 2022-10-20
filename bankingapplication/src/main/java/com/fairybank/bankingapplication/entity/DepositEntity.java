package com.fairybank.bankingapplication.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class DepositEntity {
	private double depositAmount;
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private AddAccountEntity addAccountEntity;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deposit_id;
	private String date;
	private String remark;
	public DepositEntity(double depositAmount, double balance, int deposit_id, String date) {
		super();
		this.depositAmount = depositAmount;
		this.deposit_id = deposit_id;
		this.date = date;
	}
	public DepositEntity() {
		
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getDeposit_id() {
		return deposit_id;
	}
	public void setDeposit_id(int deposit_id) {
		this.deposit_id = deposit_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
