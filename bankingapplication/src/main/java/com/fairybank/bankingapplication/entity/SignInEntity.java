package com.fairybank.bankingapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SignInEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loginDetailId;
	
	@OneToOne
    @JoinColumn(name = "customer_detail_id")
	private RegisterEntity resEntity;
	
	private String userName;
	private String password;
	public SignInEntity(String userName, String password,int loginDetailId) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginDetailId=loginDetailId;
	}
	
	public SignInEntity() {
		
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
	public int getLoginDetailId() {
		return loginDetailId;
	}
	public void setLoginDetailId(int loginDetailId) {
		this.loginDetailId = loginDetailId;
	}
	public RegisterEntity getRegisterEntity() {
		return resEntity;
	}
	public void setRegisterEntity(RegisterEntity registerEntity) {
		this.resEntity=registerEntity;
	}

	public RegisterEntity getResEntity() {
		return resEntity;
	}

	public void setResEntity(RegisterEntity resEntity) {
		this.resEntity = resEntity;
	}
}
