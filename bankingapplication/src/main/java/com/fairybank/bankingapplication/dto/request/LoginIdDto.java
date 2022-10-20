package com.fairybank.bankingapplication.dto.request;

public class LoginIdDto {
    private int loginDetailId;
    public LoginIdDto(int loginId) {
        this.loginDetailId = loginId;
    }
    public LoginIdDto(){

    }
    public int getLoginDetailId() {
        return loginDetailId;
    }

    public void setLoginDetailId(int loginDetailId) {
        this.loginDetailId = loginDetailId;
    }
}
