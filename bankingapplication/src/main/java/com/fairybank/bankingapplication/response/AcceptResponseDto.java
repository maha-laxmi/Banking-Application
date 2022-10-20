package com.fairybank.bankingapplication.response;

public class AcceptResponseDto {
    private int toUserAccountNumber;
    private int requestId;

    public AcceptResponseDto() {
    }

    public int getToUserAccountNumber() {
        return toUserAccountNumber;
    }

    public void setToUserAccountNumber(int toUserAccountNumber) {
        this.toUserAccountNumber = toUserAccountNumber;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
