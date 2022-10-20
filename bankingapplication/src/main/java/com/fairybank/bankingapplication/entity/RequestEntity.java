package com.fairybank.bankingapplication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;
    private String date;
    private double amount;
    @OneToOne
    @JoinColumn(name = "requestedUserAccountNumber")
    private AddAccountEntity requestAccountNumber;
    @OneToOne
    @JoinColumn(name = "requestUser")
    private RegisterEntity requestUser;
    @OneToOne
    @JoinColumn(name = "toUser")
    private RegisterEntity toUser;
    private String status;
    public RequestEntity() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public RegisterEntity getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(RegisterEntity requestUser) {
        this.requestUser = requestUser;
    }

    public RegisterEntity getToUser() {
        return toUser;
    }

    public void setToUser(RegisterEntity toUser) {
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

    public AddAccountEntity getRequestAccountNumber() {
        return requestAccountNumber;
    }

    public void setRequestAccountNumber(AddAccountEntity requestAccountNumber) {
        this.requestAccountNumber = requestAccountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
