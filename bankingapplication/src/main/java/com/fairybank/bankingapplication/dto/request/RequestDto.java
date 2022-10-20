package com.fairybank.bankingapplication.dto.request;


public class RequestDto {
    private String requestUser;
    private String toUser;
    private int requestAccountNumber;
    private double amount;
    private String date;
    public RequestDto() {
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRequestAccountNumber() {
        return requestAccountNumber;
    }

    public void setRequestAccountNumber(int accountNumber) {
        this.requestAccountNumber = accountNumber;
    }
}

