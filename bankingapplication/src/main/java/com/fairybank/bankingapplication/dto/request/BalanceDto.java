package com.fairybank.bankingapplication.dto.request;

public class BalanceDto {
    private int accountNo;
    private double balance;

    public BalanceDto() {
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
