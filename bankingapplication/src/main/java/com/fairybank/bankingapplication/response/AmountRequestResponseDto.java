package com.fairybank.bankingapplication.response;

import java.util.Date;

public class AmountRequestResponseDto {
    private int requestId;
    private String RequestUser;
    private String description;
    private double amount;
    private String date;
    private String status;


    public AmountRequestResponseDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestUser() {
        return RequestUser;
    }

    public void setRequestUser(String requestUser) {
        RequestUser = requestUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
