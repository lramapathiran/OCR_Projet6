package com.lavanya.escalade.service;

import com.lavanya.escalade.error.UserAlreadyExistException;
import com.lavanya.escalade.model.User;

public interface interfaceUserService {
	
	User save(User user) throws UserAlreadyExistException;

}
