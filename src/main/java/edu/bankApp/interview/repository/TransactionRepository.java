package edu.bankApp.interview.repository;

import java.util.List;

import edu.bankApp.interview.model.Transaction;

public interface TransactionRepository {

	List<Transaction> getTransactionByAccountNo(Long accNo);

}
