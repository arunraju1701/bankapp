package edu.bankApp.interview.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.bankApp.interview.forms.UserForm;
import edu.bankApp.interview.model.User;
import edu.bankApp.interview.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> register(
			 @RequestHeader(name = "authToken", required = false) String authToken,
			@RequestBody UserForm userForm, HttpServletRequest request, HttpServletResponse response) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		User existingUser = userRepository.getByUserName(userForm.getUserName());
		
		if(existingUser != null) {
			apiResponse.put("result", "Failed");
			apiResponse.put("message", "User Already register with the provided User Name");
			apiResponse.put("registered","Yes");
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		} else
		{
			User user = new User();
			user.setUserName(userForm.getUserName());
			user.setPassword(userForm.getPassword());
			user = userRepository.saveUser(user);
			apiResponse.put("result", "Success");
			apiResponse.put("message", "User Register SuccessFully");

		}
	
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Object>> login(
			@RequestBody UserForm userForm, HttpServletRequest request, HttpServletResponse response) {

		
		Map<String, Object> apiResponse = new HashMap<>();
		User user = userRepository.getByUserNameAndPassword(userForm.getUserName(), userForm.getPassword());
		if(user == null) {
			apiResponse.put("result", "Failed");
			apiResponse.put("message", "Invalid Credentials");
			apiResponse.put("registered","No");
			return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);
		}
		String authToken = UUID.randomUUID().toString();
		user.setAuthToken(authToken);
		userRepository.updateUser(user);
		apiResponse.put("result", "Success");
		apiResponse.put("authToken", authToken);
		apiResponse.put("message", "User LoggedIn Successfully");
			
		return new ResponseEntity<Map<String, Object>>(apiResponse, HttpStatus.OK);

	}
}
