package com.fairybank.entity;

import java.sql.Date;

import javax.persistence.Entity;

@Entity

public class Registerdetailentity {
	
	private String firstName;
	private String lastName;
	private String fatherName;
	private String motherName;
	private Date dob;
	private Long mobileNo;
	private Long aadharCard;
	private String state;
	private String district;
	private String gender;
	private String occupation;
	public Registerdetailentity(String firstName, String lastName, String fatherName, String motherName, Date dob,
			Long mobileNo, Long aadharCard, String state, String district, String gender, String occupation) {
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
		this.gender = gender;
		this.occupation = occupation;
	}
	public Registerdetailentity() {
		
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
}
