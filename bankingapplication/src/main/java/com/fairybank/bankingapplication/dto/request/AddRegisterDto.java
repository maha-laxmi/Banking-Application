package com.fairybank.bankingapplication.dto.request;

import java.sql.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class AddRegisterDto {
	@NotBlank(message = "FirstName cannot be blank")
	private String firstName;
	@NotBlank(message = "LastName cannot be Blank")
	private String lastName;
	private String fatherName;
	private String motherName;
	private Date dob;

	@Min(value=10, message="Should have 10 digit only")
	private Long mobileNo;
	@NotNull(message = "Aadhaar is required")
	@Min(value=12,message="Aadhaar card number must have atleast 12 digit")
	private Long aadharCard;
	@NotBlank(message = "UserName is required")
	private String userName;
	@NotBlank(message = "password is required")
	private String password;
	private String state;
	private String district;
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

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
