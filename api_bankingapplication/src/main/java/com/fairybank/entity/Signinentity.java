package com.fairybank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Signinentity {
	@Id
	private int loginDetailId;
	private String userName;
	private String password;
	public Signinentity(String userName, String password,int loginDetailId) {
		super();
		this.userName = userName;
		this.password = password;
		this.loginDetailId=loginDetailId;
	}
	public Signinentity() {
		
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
	
}
