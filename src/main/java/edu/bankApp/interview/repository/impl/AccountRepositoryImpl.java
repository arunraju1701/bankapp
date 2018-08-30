package edu.bankApp.interview.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.bankApp.interview.configuration.AbstractDao;
import edu.bankApp.interview.model.Account;
import edu.bankApp.interview.model.Bank;
import edu.bankApp.interview.model.User;
import edu.bankApp.interview.repository.AccountRepository;
import edu.bankApp.interview.repository.BankRepository;
import edu.bankApp.interview.repository.UserRepository;

@Repository
public class AccountRepositoryImpl extends AbstractDao<Integer, Account> implements AccountRepository {

	private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

	@Override
	@Transactional
	public Account saveAccount(Account account) {
		persist(account);
		return getByKey(account.getId());
	}

	@Override
	public Account getById(Integer accountId) {
		return getByKey(accountId);
	}

	@Override
	@Transactional
	public Account updateAccount(Account account) {
		return update(account);
	}

	@Override
	public List<Account> getAccountsByBankName(String bankName) {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Root<Account> accountRoot = criteriaQuery.from(Account.class);
		criteriaQuery.select(accountRoot).distinct(true);

		if (bankName != null) {
			predicates.add(criteriaBuilder.equal(accountRoot.get("bank").get("name"), bankName));
		}

		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<Account> query = getEntityManager().createQuery(criteriaQuery);

		List<Account> accountList = (List<Account>) query.getResultList();

		return accountList;
	}

	@Override
	public Account getByAccountNumber(Long accountNo) {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Root<Account> accountRoot = criteriaQuery.from(Account.class);
		criteriaQuery.select(accountRoot).distinct(true);

		predicates.add(criteriaBuilder.equal(accountRoot.get("accNo"), accountNo));

		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<Account> query = getEntityManager().createQuery(criteriaQuery);

		List<Account> accountList = (List<Account>) query.getResultList();
		
		if(accountList != null) {
			for(Account acc :accountList ) {
				return acc;
			}
		}

		return null;
	}

}
