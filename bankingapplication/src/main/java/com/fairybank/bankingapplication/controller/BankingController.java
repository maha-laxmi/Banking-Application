package com.fairybank.bankingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import com.fairybank.bankingapplication.dto.request.*;
import com.fairybank.bankingapplication.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fairybank.bankingapplication.repository.AddAccountRepository;
import com.fairybank.bankingapplication.repository.DepositRepository;
import com.fairybank.bankingapplication.repository.SignInRepository;
import com.fairybank.bankingapplication.repository.TransactionHistoryRepository;
import com.fairybank.bankingapplication.service.BankingService;


@RestController
@CrossOrigin("*")
public class BankingController{
	@Autowired
	BankingService bankingservice;
	
	@Autowired
	AddResponse addResponse;
	
	@Autowired
	private  SignInRepository signInRepository;
	
	@Autowired
	AddAccountRepository accountRepository;
	
	@Autowired
	DepositRepository depositRepository;
		
	@Autowired
	TransactionHistoryRepository transactionHistoryReposistory;
	
	@PostMapping("/customer/register")
	public ResponseEntity<AddResponse> registration(@Valid @RequestBody AddRegisterDto registerEntity) {
		AddResponse addResponse= bankingservice.register(registerEntity);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());

	}
	@PostMapping("/customer/checkUserName")
	public ResponseEntity<AddResponse> userName(@RequestBody SignInDto signInDto){
		AddResponse addResponse=bankingservice.checkUserNameExist(signInDto);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());
	}
	@PostMapping("/customer/checkaadharCard")
	public ResponseEntity<AddResponse> aadharCard(@Valid @RequestBody AddRegisterDto addRegisterDto){
		return new ResponseEntity<AddResponse>(bankingservice.aadhaarCard(addRegisterDto),HttpStatus.CREATED);
		
	}
	@PostMapping("/customer/signin")
	public ResponseEntity<AddResponse> signIn(@Valid @RequestBody SignInDto signInEntity) {
		return new ResponseEntity<AddResponse>(bankingservice.signIn(signInEntity),HttpStatus.ACCEPTED);
		
	} 
	@PostMapping("/account/add")
	public ResponseEntity<AddResponse> addAccount(@RequestBody AddAccountDto accountEntity) {
		AddResponse addResponse=bankingservice.addAccount(accountEntity);
		return new ResponseEntity<AddResponse>(addResponse ,addResponse.getStatus());
		
	}
	@PostMapping("/listofaccount")
	public ResponseEntity<List<AccountNumberDto>> loginId(@RequestBody LoginIdDto loginIdDto){
		return new ResponseEntity<>(bankingservice.getListOfAccount(loginIdDto),HttpStatus.OK);
	}
	@PutMapping("/account/deposit/{id}")
	public ResponseEntity<AddResponse> depositAmount(@PathVariable(value = "id") int id,
													 @RequestBody DepositDto depositEntity){
		AddResponse addResponse= bankingservice.deposit(depositEntity,id);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());
	}
	@PutMapping("/account/transferAmount")
	public ResponseEntity<AddResponse> transferAmount(@RequestBody TransferAmountDto transferAmountDto ) {
		AddResponse addResponse=bankingservice.transferAmount(transferAmountDto);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());
	}
	@GetMapping("/account/transactionhistory/{id}")
	public  ResponseEntity<List<TransactionHistoryResponseDto>> getTransactionHistory(@PathVariable int id){
		List<TransactionHistoryResponseDto> addResponse=bankingservice.transactionHistory(id);
		return new ResponseEntity<>(addResponse,HttpStatus.OK);
	}
	@GetMapping("/balance/{accountNo}")
	public ResponseEntity<BalanceDto> getBalance(@PathVariable(value = "accountNo") int accountNo){
		return new ResponseEntity<BalanceDto>(bankingservice.balance(accountNo),HttpStatus.OK);
	}
	@PostMapping("/account/request")
	public ResponseEntity<AddResponse> postRequestDetail(@RequestBody RequestDto requestDto){
		AddResponse addResponse=bankingservice.requestAmount(requestDto);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());
	}
	@GetMapping("/getRequest/{toUser}")
	public ResponseEntity<List<AmountRequestResponseDto>> getRequest(@PathVariable(value="toUser") String toUser){
		List<AmountRequestResponseDto> addResponse=bankingservice.requestAmountFromUser(toUser);
		return new ResponseEntity<>(addResponse,HttpStatus.OK);
	}
	@PutMapping("/acceptUserRequest")
	public ResponseEntity<AddResponse> accept(@RequestBody AcceptResponseDto acceptResponseDto){
		AddResponse addResponse=bankingservice.acceptRequest(acceptResponseDto);
		return new ResponseEntity<AddResponse>(addResponse,HttpStatus.OK);
	}
	@PostMapping("/rejectRequest")
	public ResponseEntity<AddResponse> reject(@RequestBody RejectDto rejectDto){
		AddResponse addResponse=bankingservice.rejectRequest(rejectDto);
		return new ResponseEntity<AddResponse>(addResponse,addResponse.getStatus());
	}
	@GetMapping("/getRequestUser/{requestUser}")
	public ResponseEntity<List<RequestUserDto>> getRequestUser(@PathVariable(value="requestUser") String requestUser){
		List<RequestUserDto> addResponse=bankingservice.requestUser(requestUser);
		return new ResponseEntity<>(addResponse,HttpStatus.OK);
	}

}
	
