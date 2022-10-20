package com.fairybank.bankingapplication.response;

import com.fairybank.bankingapplication.dto.request.RegisterValidateDto;
import com.fairybank.bankingapplication.entity.RegisterEntity;

import java.util.List;

public class ErrorResponseToastDto {
    private List<RegisterValidateDto> errorMessage;
    private boolean validate;

    public ErrorResponseToastDto() {
    }
    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public List<RegisterValidateDto> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<RegisterValidateDto> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
