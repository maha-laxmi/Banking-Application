package com.fairybank.bankingapplication.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class TransferAmountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private BigDecimal amount;
	private String date;
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "fromAccount")
	private AddAccountEntity addAccountEntity;

	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "toAccount")
	private AddAccountEntity addAccount;
	private String remark;
	public TransferAmountEntity( int accountNo, BigDecimal amount) {
		super();
		this.amount = amount;
	}
	public TransferAmountEntity() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AddAccountEntity getAddAccount() {
		return addAccount;
	}
	public void setAddAccount(AddAccountEntity addAccount) {
		this.addAccount = addAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
