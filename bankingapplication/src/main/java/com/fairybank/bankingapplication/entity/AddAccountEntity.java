package com.fairybank.bankingapplication.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AddAccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String accountType;
	private double balance;
	private long accountNumber;
	@ManyToOne
	@JoinColumn(name="customer_detail_id")
	private RegisterEntity registerEntity;
	@OneToMany(mappedBy ="addAccountEntity" , fetch = FetchType.EAGER)
	private Set<DepositEntity> depositEntity;
	@OneToMany(mappedBy = "addAccountEntity")
	private Set<TransferAmountEntity> transferAmountEntity;
	@OneToMany(mappedBy = "addAccountEntity")
	private Set<TransactionHistoryEntity> transactionHistoryEntity;
	public AddAccountEntity() {
		
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Set<TransactionHistoryEntity> getTransactionHistoryEntity() {
		return transactionHistoryEntity;
	}

	public void setTransactionHistoryEntity(Set<TransactionHistoryEntity> transactionHistoryEntity) {
		this.transactionHistoryEntity = transactionHistoryEntity;
	}

	public RegisterEntity getResEntity() {
		return registerEntity;
	}
	public void setResEntity(RegisterEntity resEntity) {
		this.registerEntity = resEntity;
	}

	public RegisterEntity getRegisterEntity() {
		return registerEntity;
	}

	public void setRegisterEntity(RegisterEntity registerEntity) {
		this.registerEntity = registerEntity;
	}

	public Set<DepositEntity> getDepositEntity() {
		return depositEntity;
	}

	public void setDepositEntity(Set<DepositEntity> depositEntity) {
		this.depositEntity = depositEntity;
	}

	public Set<TransferAmountEntity> getTransferAmountEntity() {
		return transferAmountEntity;
	}

	public void setTransferAmountEntity(Set<TransferAmountEntity> transferAmountEntity) {
		this.transferAmountEntity = transferAmountEntity;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
