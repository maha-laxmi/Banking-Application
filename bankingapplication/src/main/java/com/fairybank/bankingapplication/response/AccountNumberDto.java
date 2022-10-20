package com.fairybank.bankingapplication.response;

public class AccountNumberDto {
    private long accountNumber;
    private int id;
    public AccountNumberDto(){

    }


    public int getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
}
