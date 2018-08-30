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
import edu.bankApp.interview.model.User;
import edu.bankApp.interview.repository.UserRepository;

@Repository
public class UserRepositoryImpl extends AbstractDao<Integer, User> implements UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Override
	@Transactional
	public User saveUser(User user) {
		persist(user);
		return getByKey(user.getId());
	}

	@Override
	public User getById(Integer userId) {
		return getByKey(userId);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return update(user);
	}

	@Override
	public User getByUserName(String userName) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		

		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(userRoot).distinct(true);
		
		predicates.add(criteriaBuilder.equal(userRoot.get("userName"), userName));
		
		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<User> query = getEntityManager().createQuery(criteriaQuery);
		
		List<User> userList = (List<User>) query.getResultList();
		
		if(userList != null) {
			for(User user : userList) {
				return user;
			}
		}

		return null;
	}
	
	@Override
	public User getByUserNameAndPassword(String userName, String password) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(userRoot).distinct(true);
		
		predicates.add(criteriaBuilder.equal(userRoot.get("userName"), userName));
		predicates.add(criteriaBuilder.equal(userRoot.get("password"), password));
		
		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<User> query = getEntityManager().createQuery(criteriaQuery);
		
		List<User> userList = (List<User>) query.getResultList();

		if(userList != null) {
			for(User user : userList) {
				return user;
			}
		}

		return null;
	}
	@Override
	public User getByAuthToken(String authToken) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(userRoot).distinct(true);
		
		predicates.add(criteriaBuilder.equal(userRoot.get("authToken"), authToken));
		
		if (!predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		}
		TypedQuery<User> query = getEntityManager().createQuery(criteriaQuery);
		
		List<User> userList = (List<User>) query.getResultList();

		if(userList != null) {
			for(User user : userList) {
				return user;
			}
		}

		return null;
	}

}
