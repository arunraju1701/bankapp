package edu.bankApp.interview.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bankApp.interview.forms.UserForm;
import edu.bankApp.interview.model.Account;
import edu.bankApp.interview.model.Bank;
import edu.bankApp.interview.model.Transaction;
import edu.bankApp.interview.model.User;
import edu.bankApp.interview.repository.AccountRepository;
import edu.bankApp.interview.repository.BankRepository;
import edu.bankApp.interview.repository.TransactionRepository;
import edu.bankApp.interview.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class BankController {
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	@RequestMapping(value = "/getbanklist", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> getbanklist(
			 @RequestHeader(name = "authToken", required = false) String authToken) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		
		List<Bank> bankList = bankRepository.getByBankName(null);
		
		if(bankList == null || bankList.isEmpty()) {
			bankList = new ArrayList<>();
		}
		
		apiResponse.put("bankList", bankList);
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getbankdata/{bankName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> getBankData(
			 @RequestHeader(name = "authToken", required = false) String authToken,
			 @PathVariable String bankName) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		
		List<Bank> bankList = new ArrayList<Bank>();
		
		if(bankName != null) {
			bankList = bankRepository.getByBankName(bankName);
		} 
		if(bankList == null || bankList.isEmpty()) {
			apiResponse.put("result", "Success");
			apiResponse.put("message", "No Data Found");
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

		}
		apiResponse.put("result", "Success");
		apiResponse.put("Username", bankList.get(0).isUserNameRequired() ? "Yes" : "No");
		apiResponse.put("Password", bankList.get(0).isPasswordRequired()? "Yes" : "No");
		if(bankList.get(0).getCorp_id() != null) {
			apiResponse.put("CorpId", "yes");
		} else {
			apiResponse.put("CorpId", "no");
		}

			
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
	@RequestMapping(value = "/logintobank/{bankName}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> loginToBank(
			 @RequestHeader(name = "authToken", required = false) String authToken,
			 @PathVariable String bankName, @RequestBody UserForm userForm) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		
		User user = userRepository.getByUserNameAndPassword(userForm.getUserName(), userForm.getPassword());
		if(user == null) {
			apiResponse.put("result", "Failed");
			apiResponse.put("message", "You are not authorized to log into " + bankName);
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		} 
		
		apiResponse.put("result", "Success");
		apiResponse.put("authToken", user.getAuthToken());
		
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getaccounts/{bankName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> getAccounts(
			 @RequestHeader(name = "authToken", required = true) String authToken,
			 @PathVariable String bankName) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		User user = userRepository.getByAuthToken(authToken);
		if(user == null) {
			apiResponse.put("result", "Failed");
			apiResponse.put("message", "You are not authorized to log into " + bankName);
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		} 
		List<Account> accounts = accountRepository.getAccountsByBankName(bankName);

		if (accounts == null || accounts.isEmpty()) {
			apiResponse.put("result", "Success");
			apiResponse.put("accounts", new ArrayList<>());
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		}
		apiResponse.put("result", "Success");
		apiResponse.put("accounts", accounts);
			
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/gettransactiondata/{accountNumber}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> getTransactionData(
			 @RequestHeader(name = "authToken", required = false) String authToken, @PathVariable Long accountNumber) {
		
		Map<String, Object> apiResponse = new HashMap<>();
		List<Transaction> transactions = transactionRepository.getTransactionByAccountNo(accountNumber);

		if (transactions == null || transactions.isEmpty()) {
			apiResponse.put("result", "Failed");
			apiResponse.put("message", "No Transactions Found for the Account Number");
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		}
		apiResponse.put("result", "Success");
		apiResponse.put("transactions", transactions);
			
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
}
