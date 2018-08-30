package edu.bankApp.interview.repository;

import java.util.List;

import edu.bankApp.interview.model.Account;

public interface AccountRepository {

	Account saveAccount(Account account);

	Account getById(Integer accountId);
	
	Account getByAccountNumber(Long accountNo);
	
	Account updateAccount(Account account);

	List<Account> getAccountsByBankName(String bankName);

}
