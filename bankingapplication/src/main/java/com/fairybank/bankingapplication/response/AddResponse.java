package com.fairybank.bankingapplication.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AddResponse {
	private String msg;
	private int id;
	private HttpStatus status;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public 	HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus accepted) {
		this.status = accepted;
	}
	

}
