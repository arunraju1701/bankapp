package edu.bankApp.interview.repository;

import java.util.List;

import edu.bankApp.interview.model.Bank;

public interface BankRepository {

	Bank saveBank(Bank bank);

	Bank getById(Integer bankId);
	
	Bank getByCorpId(Integer bankCorpId);

	Bank updateBank(Bank bank);

	List<Bank> getByBankName(String bankName);

}
