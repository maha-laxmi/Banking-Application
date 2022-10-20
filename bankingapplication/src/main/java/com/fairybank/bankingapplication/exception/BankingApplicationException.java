package com.fairybank.bankingapplication.exception;

public class BankingApplicationException extends RuntimeException{
	private int status;
	private String message;
	
	public BankingApplicationException(int status, String msg) {
		super();
		this.status = status;
		this.message = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
