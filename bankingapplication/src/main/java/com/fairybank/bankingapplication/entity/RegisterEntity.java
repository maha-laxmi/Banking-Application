package com.fairybank.bankingapplication.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
//@SequenceGenerator(name = "sequence",sequenceName = "my_sequence", initialValue = 1000, allocationSize = 1000)
public class RegisterEntity {
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private Date dob;
	private Long mobileNo;
	@Column(unique = true)
	private Long aadharCard;
	
	private String state;
	private String district;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerDetailId;

	private int accountId;

	@OneToMany(mappedBy = "registerEntity")
	private Set<AddAccountEntity> addAccountEntity;
	
	public RegisterEntity(String firstName, String lastName, String fatherName, String motherName, Date dob,
			Long mobileNo, Long aadharCard, String state, String district,
			int customerDetailId, int accountId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.aadharCard = aadharCard;
		this.state = state;
		this.district = district;
		this.customerDetailId = customerDetailId;
		this.accountId = accountId;
	}
	public RegisterEntity() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(Long aadharCard) {
		this.aadharCard = aadharCard;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public int getCustomerDetailId() {
		return customerDetailId;
	}
	public void setCustomerDetailId(int customerDetailId) {
		this.customerDetailId = customerDetailId;
	}
	
	public Set<AddAccountEntity> getAccountEntity() {
		return addAccountEntity;
	}
	public void setAddAccountEntity(Set<AddAccountEntity> addAccountEntity) {
		this.addAccountEntity=addAccountEntity;
	}
}
