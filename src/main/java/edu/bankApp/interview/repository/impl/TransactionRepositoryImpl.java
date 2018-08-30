package edu.bankApp.interview.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.bankApp.interview.configuration.AbstractDao;
import edu.bankApp.interview.model.Transaction;
import edu.bankApp.interview.repository.TransactionRepository;

@Repository
public class TransactionRepositoryImpl extends AbstractDao<Integer, Transaction> implements TransactionRepository {

	@Override
	public List<Transaction> getTransactionByAccountNo(Long accNo) {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Root<Transaction> transactionRoot = criteriaQuery.from(Transaction.class);
		criteriaQuery.select(transactionRoot).distinct(true);

		predicates.add(criteriaBuilder.equal(transactionRoot.get("account").get("accNo"), accNo));

		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<Transaction> query = getEntityManager().createQuery(criteriaQuery);

		List<Transaction> transactionList = (List<Transaction>) query.getResultList();

		return transactionList;
	}
	
}
