package com.fairybank.bankingapplication.GlobalExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fairybank.bankingapplication.exception.BankingApplicationException;
import com.fairybank.bankingapplication.response.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> error(BankingApplicationException bankingApplicationException) {
		ErrorResponse errorResponse=new ErrorResponse(bankingApplicationException.getMessage(), null);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.valueOf(bankingApplicationException.getStatus()));		 
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       List<ErrorResponse> errorResponse=new ArrayList<>();
	   ex.getBindingResult().getAllErrors().forEach(error->{
			ErrorResponse errorResponse1=new ErrorResponse();
		   errorResponse1.setDetails(((FieldError)error).getField());
		   errorResponse1.setMessage(error.getDefaultMessage());
		   errorResponse.add(errorResponse1);
		});

		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));

	}

}