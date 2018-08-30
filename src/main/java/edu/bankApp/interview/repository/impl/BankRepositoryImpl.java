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
import edu.bankApp.interview.model.Bank;
import edu.bankApp.interview.model.User;
import edu.bankApp.interview.repository.BankRepository;
import edu.bankApp.interview.repository.UserRepository;

@Repository
public class BankRepositoryImpl extends AbstractDao<Integer, Bank> implements BankRepository {

	private static final Logger logger = LoggerFactory.getLogger(BankRepositoryImpl.class);

	@Override
	@Transactional
	public Bank saveBank(Bank bank) {
		persist(bank);
		return getByKey(bank.getId());
	}

	@Override
	public Bank getById(Integer bankId) {
		return getByKey(bankId);
	}

	@Override
	@Transactional
	public Bank updateBank(Bank bank) {
		return update(bank);
	}

	@Override
	public List<Bank> getByBankName(String bankName) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		

		Root<Bank> bankRoot = criteriaQuery.from(Bank.class);
		criteriaQuery.select(bankRoot).distinct(true);
		
		if(bankName != null) {
			predicates.add(criteriaBuilder.equal(bankRoot.get("name"), bankName));
		}
		
		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<Bank> query = getEntityManager().createQuery(criteriaQuery);
		
		List<Bank> bankList = (List<Bank>) query.getResultList();
		
		return bankList;
	}

	@Override
	public Bank getByCorpId(Integer bankCorpId) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Bank> criteriaQuery = criteriaBuilder.createQuery(Bank.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		

		Root<Bank> bankRoot = criteriaQuery.from(Bank.class);
		criteriaQuery.select(bankRoot).distinct(true);
		
		predicates.add(criteriaBuilder.equal(bankRoot.get("corpId"), bankCorpId));
		
		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<Bank> query = getEntityManager().createQuery(criteriaQuery);
		
		List<Bank> bankList = (List<Bank>) query.getResultList();
		
		if(bankList != null) {
			for(Bank bank : bankList) {
				return bank;
			}
		}

		return null;
	}
}
