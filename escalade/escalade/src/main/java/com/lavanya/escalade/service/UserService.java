package com.lavanya.escalade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.escalade.model.User;
import com.lavanya.escalade.repository.UserRepository;

@Service
public class UserService {
	
	// This means to get the bean called userRepository
	//  Which is auto-generated by Spring, we will use it to handle the data
	@Autowired
	private UserRepository userRepository;
	
	public List<User> usersList() {
		return userRepository.findAll();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User getUserById(int id) {
		
		Optional<User>  userResponse = userRepository.findById(id);
		User user = userResponse.get();
		return user;
	}
}