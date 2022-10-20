package com.fairybank.bankingapplication.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fairybank.bankingapplication.dto.request.*;
import com.fairybank.bankingapplication.entity.*;
import com.fairybank.bankingapplication.repository.*;
import com.fairybank.bankingapplication.response.*;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fairybank.bankingapplication.exception.BankingApplicationException;

@Service
public class BankingService {
	
	private static final Logger logger=LoggerFactory.getLogger(BankingService.class);
	
	@Autowired
	RegisterRepository registerRepository;
	
	@Autowired
	SignInRepository signInRepository;
	
	@Autowired
	AddAccountRepository accountRepository;
	
	@Autowired
	DepositRepository depositRepository;
	
	@Autowired
	TransferAmountRepository amountRepository;
	
	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	@Autowired
	RequestRepository requestRepository;
	public boolean checkAlreadyExitCustomerAadhaarCard(Long id) {
		Optional<RegisterEntity> aadhaarCard =registerRepository.findByAadharCard(id);
		return aadhaarCard.isPresent();
	}
	public AddResponse aadhaarCard(AddRegisterDto registerDto) {
		RegisterEntity registerEntity=new RegisterEntity();
		AddResponse addResponse=new AddResponse();
		Long id=registerDto.getAadharCard();
			if(!checkAlreadyExitCustomerAadhaarCard(id)) {
				logger.info("Aadhaar");
				registerEntity.setAadharCard(registerDto.getAadharCard());
				registerRepository.save(registerEntity);
				addResponse.setMsg("Aadhaar Card is added successfully");
			    addResponse.setStatus(HttpStatus.CREATED);
			    addResponse.setId(registerEntity.getCustomerDetailId());
			}
			else {
				addResponse.setMsg("Aadhaar Card is already exists");
			    addResponse.setStatus(HttpStatus.BAD_REQUEST);
			    addResponse.setId(registerEntity.getCustomerDetailId());
			    throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(),"");
			}
		
		
		return addResponse;
	}
	public AddResponse register(AddRegisterDto registerDto) {
		RegisterEntity register=new RegisterEntity();
		SignInEntity signInEntity=new SignInEntity();
		AddResponse addResponse=new AddResponse();
		try {
				logger.info("Customer is added successfully");
				logger.info(registerDto.getUserName());
				logger.info(registerDto.getPassword());
				register.setFirstName(registerDto.getFirstName());
				register.setLastName(registerDto.getLastName());
				register.setDistrict(registerDto.getDistrict());
				register.setDob(registerDto.getDob());
				register.setMobileNo(registerDto.getMobileNo());
				register.setFatherName(registerDto.getFatherName());
				register.setMotherName(registerDto.getMotherName());
				register.setAadharCard(registerDto.getAadharCard());
				register.setState(registerDto.getState());
				register.setMotherName(registerDto.getMotherName());
				RegisterEntity resEntity = registerRepository.save(register);
		    	signInEntity.setUserName(registerDto.getUserName());
			    signInEntity.setPassword(registerDto.getPassword());
				boolean user=signInRepository.existsByUserName(signInEntity.getUserName());
				if(user){
					throw new BankingApplicationException(HttpStatus.CONFLICT.value(),"UserName already exist");
				}
				else{
					signInEntity.setRegisterEntity(resEntity);
					signInRepository.save(signInEntity);
				}
				addResponse.setMsg("User Registration Successful! Please Login.");
				addResponse.setStatus(HttpStatus.CREATED);
			}
			 catch (BankingApplicationException e) {
			logger.error(e.getMessage());
				throw new BankingApplicationException(e.getStatus(),e.getMessage());
			}
	     	return addResponse;
		
	}
	public SignInEntity checkSignInDetail(String name,String password) {
		Optional<SignInEntity> user=signInRepository.findByUserName(name);
		SignInEntity res = null;
		if(user.isPresent()) {
			SignInEntity  signInEntity=user.get();
			if(signInEntity.getPassword().equals(password)) {
				res = signInEntity;
			}
		}
		return res;
	}
	
	public AddResponse signIn(SignInDto signInDto) {
		AddResponse addResponse=new AddResponse();
		String name=signInDto.getUserName();
		String password=signInDto.getPassword();
		SignInEntity res  = checkSignInDetail(name,password);
		if(Optional.ofNullable(res).isPresent() )
		{
			addResponse.setMsg("Logged in successfully!");
			addResponse.setStatus(HttpStatus.ACCEPTED);
			addResponse.setId(res.getLoginDetailId());
		}else {
			throw new BankingApplicationException(HttpStatus.NOT_FOUND.value(),"Invalid credentials");
		}
		return addResponse;
		
	}
	public List<AccountNumberDto> getListOfAccount(LoginIdDto loginIdDto){

		int id=loginIdDto.getLoginDetailId();
			Optional<SignInEntity> loginIdDetail = signInRepository.findById(id);
			SignInEntity signIn=loginIdDetail.get();
			List<AddAccountEntity> account=accountRepository.findByRegisterEntity(signIn.getRegisterEntity());
		    List<AccountNumberDto> accountNumber=new ArrayList<>();
			if(!account.isEmpty()){
				for(AddAccountEntity userDetail:account){
					AccountNumberDto accountNumberDto=new AccountNumberDto();
					accountNumberDto.setAccountNumber(userDetail.getAccountNumber());
					accountNumberDto.setId(userDetail.getId());
					accountNumber.add(accountNumberDto);
				}

			}
		return accountNumber;
	}
	public AddResponse checkUserNameExist(SignInDto signInDto){
		AddResponse addResponse=new AddResponse();
		String name=signInDto.getUserName();
		Optional<SignInEntity> userName=signInRepository.findByUserName(name);
		if(userName.isPresent()){
			addResponse.setMsg("UserName already exist");
			addResponse.setStatus(HttpStatus.CONFLICT);
		}
		else{
			addResponse.setMsg("UserName does not exist");
			addResponse.setStatus(HttpStatus.OK);
		}
		return addResponse;
	}
	public boolean checkAccountNumber(long accountNo) {
		Optional<AddAccountEntity> accountNumber =accountRepository.findByAccountNumber(accountNo);
		return accountNumber.isPresent();
	}
	public AddResponse addAccount(AddAccountDto accountDto) {
		AddAccountEntity accountEntity=new AddAccountEntity();
		AddResponse addResponse=new AddResponse();
		long accountNumber=accountDto.getAccountNumber();
		double amount=accountDto.getInitialAmount();
		try {
			if (!checkAccountNumber(accountNumber)) {
				if(amount>=500) {
					Optional<SignInEntity> loginDetail = signInRepository.findById(accountDto.getLoginId());
					if (loginDetail.isPresent()) {
						accountEntity.setAccountNumber(accountDto.getAccountNumber());
						accountEntity.setAccountType(accountDto.getAccountType());
						accountEntity.setBalance(accountDto.getInitialAmount());
						accountEntity.setResEntity(loginDetail.get().getRegisterEntity());
						AddAccountEntity addAccountEntity = accountRepository.save(accountEntity);
						TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
						transactionHistoryEntity.setAddAccountEntity(addAccountEntity);
						transactionHistoryEntity.setCredit(accountDto.getInitialAmount());
						transactionHistoryEntity.setBalance(accountDto.getInitialAmount());
						transactionHistoryEntity.setDescription("Initial amount added successfully");
						transactionHistoryEntity.setDebit(0);
						LocalDate localDate = LocalDate.now();
						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						String date = dateTimeFormatter.format(localDate);
						transactionHistoryEntity.setDate(date);
						transactionHistoryRepository.save(transactionHistoryEntity);
						addResponse.setMsg("Account successfully added ");
						addResponse.setId(accountEntity.getId());
						addResponse.setStatus(HttpStatus.OK);


					} else{
						addResponse.setMsg("Invalid Data");
						addResponse.setStatus(HttpStatus.BAD_REQUEST);
					}
				}
				else {
					throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(), "Please enter initial amount 500 or more");
				}

			} else {
				throw new BankingApplicationException(HttpStatus.FOUND.value(), "Account number already exist");
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		}
		return addResponse;
	}

	public AddResponse deposit(DepositDto depositDto,int accountNumber) {
		AddResponse addResponse=new AddResponse();
		DepositEntity depositEntity=new DepositEntity();
		logger.info("success->{}",accountNumber);
		try {
				Optional<AddAccountEntity> addAccountEntity = accountRepository.findByAccountNumber(accountNumber);
				if(depositDto.getDepositAmount()!=0){
				if (addAccountEntity.isPresent()) {
					depositEntity.setDate(depositDto.getDate());
					double depositAmount = depositDto.getDepositAmount();
					AddAccountEntity accountEntity = addAccountEntity.get();
					double balance = accountEntity.getBalance();
					double totalBalance = depositAmount + balance;
					accountEntity.setBalance(totalBalance);
					accountRepository.save(accountEntity);
					depositEntity.setDepositAmount(depositAmount);
					depositEntity.setAddAccountEntity(addAccountEntity.get());
					depositEntity.setRemark(depositDto.getRemark());
					depositRepository.save(depositEntity);
					TransactionHistoryEntity transactionHistory = new TransactionHistoryEntity();
					transactionHistory.setBalance(totalBalance);
					transactionHistory.setDate(depositDto.getDate());
					transactionHistory.setCredit(depositAmount);
					transactionHistory.setDebit(0);
					transactionHistory.setDescription("Self Deposit");
					transactionHistory.setAddAccountEntity(accountEntity);
					transactionHistory.setRemark(depositDto.getRemark());
					TransactionHistoryEntity transactionHistoryEntity = transactionHistoryRepository.save(transactionHistory);
					addResponse.setId(transactionHistoryEntity.getTransactionHistory_id());
					addResponse.setMsg("Deposit successful");
					addResponse.setStatus(HttpStatus.OK);
				}
				else {
					throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(),"Transaction failed");
				}

			} else {
					addResponse.setMsg("Amount field cannot be empty!");
					addResponse.setStatus(HttpStatus.CONFLICT);
				}
		}
		catch (Exception e) {
			addResponse.setMsg("Invalid Operation");
			addResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		return addResponse;
		
	}
	public boolean checkAccountIdExit(int toAccount,int fromAccount) {
		Optional<AddAccountEntity> transferId=accountRepository.findByAccountNumber(toAccount);
		Optional<AddAccountEntity> depositId=accountRepository.findByAccountNumber(fromAccount);
		return transferId.isPresent() && depositId.isPresent();
	}
	
	public AddResponse transferAmount(TransferAmountDto transferAmountDto) {
		AddResponse addResponse=new AddResponse();
		TransferAmountEntity transferAmountEntity=new TransferAmountEntity();
		int toAccountId=transferAmountDto.getToAccount();
		int fromAccountId=transferAmountDto.getFromAccount();
		BigDecimal amount=transferAmountDto.getAmount();
		try {
			if(toAccountId!=fromAccountId ) {
				if(transferAmountDto.getAmount().doubleValue()!=0) {
					if (checkAccountIdExit(toAccountId, fromAccountId)) {
						transferAmountEntity.setAmount(amount);
						transferAmountEntity.setDate(transferAmountDto.getDate());
						Optional<AddAccountEntity> fromAccount = accountRepository.findByAccountNumber(fromAccountId);
						Optional<AddAccountEntity> toAccount = accountRepository.findByAccountNumber(toAccountId);
						AddAccountEntity sender = fromAccount.get();
						Double senderAmount = sender.getBalance();
						AddAccountEntity receiver = toAccount.get();
						Double receiverAmount = receiver.getBalance();
						if (senderAmount >= amount.doubleValue()) {
							double senderCurrentBalance = senderAmount - amount.doubleValue();
							double receiverCurrentBalance = receiverAmount + amount.doubleValue();
							sender.setBalance(senderCurrentBalance);
							receiver.setBalance(receiverCurrentBalance);
							accountRepository.save(sender);
							accountRepository.save(receiver);
							transferAmountEntity.setDate(transferAmountDto.getDate());
							transferAmountEntity.setAddAccountEntity(fromAccount.get());
							transferAmountEntity.setAddAccount(toAccount.get());
							transferAmountEntity.setRemark(transferAmountDto.getRemark());
							TransferAmountEntity transferAmount = amountRepository.save(transferAmountEntity);
							TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
							transactionHistoryEntity.setDate(transferAmountDto.getDate());
							transactionHistoryEntity.setCredit(0);
							transactionHistoryEntity.setDebit(amount.doubleValue());
							transactionHistoryEntity.setBalance(senderCurrentBalance);
							transactionHistoryEntity.setDescription("Amount Transfer to " + toAccountId);
							transactionHistoryEntity.setAddAccountEntity(sender);
							transactionHistoryEntity.setRemark(transferAmountDto.getRemark());
							transactionHistoryRepository.save(transactionHistoryEntity);
							TransactionHistoryEntity transactionHistory = new TransactionHistoryEntity();
							transactionHistory.setDate(transferAmountDto.getDate());
							transactionHistory.setCredit(amount.doubleValue());
							transactionHistory.setDebit(0);
							transactionHistory.setBalance(receiverCurrentBalance);
							transactionHistory.setDescription("Amount Transfer from " + fromAccountId);
							transactionHistory.setAddAccountEntity(receiver);
							transactionHistory.setRemark(transferAmountDto.getRemark());
							transactionHistoryRepository.save(transactionHistory);
							addResponse.setId(transferAmount.getId());
							addResponse.setMsg("Amount Transfer successfully.");
							addResponse.setStatus(HttpStatus.CREATED);
						} else {
							addResponse.setMsg("Insufficient Balance");
							addResponse.setStatus(HttpStatus.BAD_REQUEST);
						}

					} else {
						addResponse.setMsg("Account Number does not exist");
						addResponse.setStatus(HttpStatus.BAD_REQUEST);
					}
				}else{
					addResponse.setMsg("Amount field cannot be empty");
					addResponse.setStatus(HttpStatus.BAD_REQUEST);
				}
			}else{
				addResponse.setMsg("Please enter a different account number which does not match with current account number.");
				addResponse.setStatus(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(),"Transaction Failed");
		}
		return addResponse;
		
	}
	public List<TransactionHistoryResponseDto> transactionHistory(int accountNumber){
		Optional<AddAccountEntity> userDetails=accountRepository.findByAccountNumber(accountNumber);
		AddAccountEntity addAccountEntity=userDetails.get();
		List<TransactionHistoryEntity> user = transactionHistoryRepository.findByAddAccountEntityId(addAccountEntity.getId());
		if(user.isEmpty()){
			throw new BankingApplicationException(HttpStatus.NOT_FOUND.value(),"User does not exist for this id :: " + addAccountEntity);
		}
		else{
			List<TransactionHistoryResponseDto> transactionHistoryResponseDto=new ArrayList<>();
			for(TransactionHistoryEntity userDetail:user){
				TransactionHistoryResponseDto transactionHistoryResponse=new TransactionHistoryResponseDto();
				transactionHistoryResponse.setTransactionId(userDetail.getTransactionHistory_id());
				transactionHistoryResponse.setBalance(userDetail.getBalance());
				transactionHistoryResponse.setCredit(userDetail.getCredit());
				transactionHistoryResponse.setDebit(userDetail.getDebit());
				transactionHistoryResponse.setDate(userDetail.getDate());
				transactionHistoryResponse.setRemark(userDetail.getRemark());
				transactionHistoryResponse.setDescription(userDetail.getDescription());
				transactionHistoryResponseDto.add(transactionHistoryResponse);

			}
			return transactionHistoryResponseDto;
		}
	}
	public BalanceDto balance(int accountNumber){
		Optional<AddAccountEntity> addAccountEntity=accountRepository.findByAccountNumber(accountNumber);
		AddAccountEntity account=addAccountEntity.get();
		BalanceDto balanceDto=new BalanceDto();
		if(addAccountEntity.isPresent()){
			double total=account.getBalance();
			balanceDto.setBalance(total);
			balanceDto.setAccountNo(accountNumber);
		}
		else{
			throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(), "Account Number does not exist.");
		}
		return balanceDto;
	}
	public boolean checkRequestUserNameExit(int fromUserId,String toUserId,String requestUser){
		boolean userExit=requestUser.equals(toUserId);
		Optional<SignInEntity> fromUser=signInRepository.findByUserName(requestUser);
		Optional<AddAccountEntity> account=accountRepository.findByAccountNumber(fromUserId);
		Optional<SignInEntity> toUser=signInRepository.findByUserName(toUserId);
		return account.isPresent() & toUser.isPresent() & fromUser.isPresent() && !userExit;
	}

	public AddResponse requestAmount(RequestDto requestDto) {
		AddResponse addResponse=new AddResponse();
		RequestEntity request=new RequestEntity();
		int requestUserAccountNumber=requestDto.getRequestAccountNumber();
		String requestUser=requestDto.getRequestUser();
		String toUserId=requestDto.getToUser();
		try{
			if(checkRequestUserNameExit(requestUserAccountNumber,toUserId,requestUser)){
				Optional<SignInEntity> requestUserName=signInRepository.findByUserName(requestUser);
				Optional<AddAccountEntity> requestUserAccountNo=accountRepository.findByAccountNumber(requestUserAccountNumber);
				Optional<SignInEntity> toUser=signInRepository.findByUserName(toUserId);
				RegisterEntity fromUserid= requestUserName.get().getRegisterEntity();
				RegisterEntity toUserid= toUser.get().getRegisterEntity();
				AddAccountEntity accountNo=requestUserAccountNo.get();
				request.setRequestUser(fromUserid);
				request.setToUser(toUserid);
				request.setAmount(requestDto.getAmount());
				request.setDate(requestDto.getDate());
				request.setRequestAccountNumber(accountNo);
				request.setStatus("InProgress");
				RequestEntity requestEntity=requestRepository.save(request);
				addResponse.setMsg("Requested");
				addResponse.setStatus(HttpStatus.OK);
			}
			else{
				addResponse.setMsg("Invalid Data");
				addResponse.setStatus(HttpStatus.CONFLICT);
			}
		}catch (Exception e){
			request.setStatus("Request Failed");
			throw new BankingApplicationException(HttpStatus.FORBIDDEN.value(), "ERROR");
		}

		return addResponse;
	}

	public List<AmountRequestResponseDto> requestAmountFromUser(String toUserEmail) {
		List<AmountRequestResponseDto> requestResponse=new ArrayList<>();
		Optional<SignInEntity> userAccountDetail=signInRepository.findByUserName(toUserEmail);
		if(userAccountDetail.isPresent()){
			RegisterEntity register= userAccountDetail.get().getRegisterEntity();
			Optional<List<RequestEntity>> toUser=requestRepository.findByToUser(register);
			if(toUser.isPresent()) {
				for (RequestEntity response : toUser.get()) {
						AmountRequestResponseDto amountRequestResponseDto = new AmountRequestResponseDto();
						amountRequestResponseDto.setAmount(response.getAmount());
						amountRequestResponseDto.setDate(response.getDate());
						amountRequestResponseDto.setRequestId(response.getRequestId());
						amountRequestResponseDto.setRequestUser(response.getRequestUser().getFirstName() + " " + response.getRequestUser().getLastName());
						amountRequestResponseDto.setStatus(response.getStatus());
						String fromUserName = response.getRequestUser().getFirstName();
						amountRequestResponseDto.setDescription("Amount requested from " + fromUserName);
						if(amountRequestResponseDto.getStatus().equals("InProgress")) {
							requestResponse.add(amountRequestResponseDto);
						}
				}
			}
		}
		return requestResponse;
	}

	public AddResponse acceptRequest(AcceptResponseDto acceptResponseDto) {
		AddResponse addResponse=new AddResponse();
		try {
			int requestId = acceptResponseDto.getRequestId();
			int acceptUserAccountNumber = acceptResponseDto.getToUserAccountNumber();
			Optional<RequestEntity> users = requestRepository.findById(requestId);
			RequestEntity request = new RequestEntity();
			AddAccountEntity requestUserAccountNumber = users.get().getRequestAccountNumber();
			Optional<AddAccountEntity> toUserAccountNumber = accountRepository.findByAccountNumber(acceptUserAccountNumber);
			Optional<AddAccountEntity> requestUserAccountNo = accountRepository.findByAccountNumber(requestUserAccountNumber.getAccountNumber());
			double amount = users.get().getAmount();
			if (users.isPresent() && toUserAccountNumber.isPresent()) {

				AddAccountEntity toUser = users.get().getRequestAccountNumber();
				double acceptUserBalance = toUserAccountNumber.get().getBalance();
				double requestUserBalance = toUser.getBalance();
				if (acceptUserBalance > amount) {
					double requestUserCurrentBalance = requestUserBalance + amount;
					double acceptUserCurrentBalance = acceptUserBalance - amount;
					toUserAccountNumber.get().setBalance(acceptUserCurrentBalance);
					toUser.setBalance(requestUserCurrentBalance);
					accountRepository.save(toUser);
					accountRepository.save(toUserAccountNumber.get());
					users.get().setStatus("Accepted");
					requestRepository.save(users.get());
					TransactionHistoryEntity transactionHistoryEntity=new TransactionHistoryEntity();
					transactionHistoryEntity.setDate(users.get().getDate());
					transactionHistoryEntity.setBalance(requestUserCurrentBalance);
					transactionHistoryEntity.setCredit(amount);
					transactionHistoryEntity.setDebit(0);
					transactionHistoryEntity.setRemark("");
					transactionHistoryEntity.setDescription("Amount credited successfully.");
					transactionHistoryEntity.setAddAccountEntity(requestUserAccountNo.get());
					transactionHistoryRepository.save(transactionHistoryEntity);
					TransactionHistoryEntity transactionHistory=new TransactionHistoryEntity();
					transactionHistory.setDebit(amount);
					transactionHistory.setDate(users.get().getDate());
					transactionHistory.setCredit(0);
					transactionHistory.setBalance(acceptUserCurrentBalance);
					transactionHistory.setRemark(users.get().getStatus());
					transactionHistory.setAddAccountEntity(toUserAccountNumber.get());
					transactionHistory.setDescription("Amount debited.");
					transactionHistoryRepository.save(transactionHistory);
					addResponse.setMsg("Payment successfully.");
					addResponse.setId(requestId);
					addResponse.setStatus(HttpStatus.ACCEPTED);

				}
				else {
					addResponse.setMsg("Insufficient Balance!");
					addResponse.setStatus(HttpStatus.BAD_REQUEST);
				}
			} else {
				addResponse.setMsg("RequestId does not exist.");
				addResponse.setStatus(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e){
			throw new BankingApplicationException(HttpStatus.BAD_REQUEST.value(),"Request Failed");
		}
		return addResponse;
	}


	public AddResponse rejectRequest(RejectDto rejectDto) {
		AddResponse addResponse=new AddResponse();
		int requestId=rejectDto.getRequestId();
		Optional<RequestEntity> request=requestRepository.findById(requestId);
		if(request.isPresent()){
			request.get().setStatus("Decline");
			requestRepository.save(request.get());
			addResponse.setMsg("Rejected");
			addResponse.setId(requestId);
			addResponse.setStatus(HttpStatus.ACCEPTED);
		}
		else{

			requestRepository.save(request.get());
			addResponse.setMsg("Request not exist");
			addResponse.setId(requestId);
			addResponse.setStatus(HttpStatus.NOT_FOUND);
		}
		return addResponse;
	}
	public List<RequestUserDto> requestUser(String requestUser) {
		List<RequestUserDto> requestResponse=new ArrayList<>();
		Optional<SignInEntity> userAccountDetail=signInRepository.findByUserName(requestUser);
		if(userAccountDetail.isPresent()){
			RegisterEntity register= userAccountDetail.get().getRegisterEntity();
			Optional<List<RequestEntity>> toUser=requestRepository.findByRequestUser(register);
			if(toUser.isPresent()) {
				for (RequestEntity detail : toUser.get()) {
					RequestUserDto requestUserDto=new RequestUserDto();
					requestUserDto.setToUser(detail.getToUser().getFirstName());
					requestUserDto.setDate(detail.getDate());
					requestUserDto.setAmount(detail.getAmount());
					requestUserDto.setStatus(detail.getStatus());
					requestResponse.add(requestUserDto);
				}
			}
		}
		return requestResponse;
	}



}
