package edu.bankApp.interview.repository;

import edu.bankApp.interview.model.User;

public interface UserRepository {

	User saveUser(User user);

	User getById(Integer userId);

	User updateUser(User user);

	User getByUserName(String userName);

	User getByUserNameAndPassword(String userName, String password);
	
	User getByAuthToken(String authToken);


}
