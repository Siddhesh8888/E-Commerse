package com.app.service;

import com.app.exception.UserException;
import com.app.model.User;


public interface UserService {
	
	User findUserById(Long id)throws UserException;
	
	User findUserProfileByJwt(String jwt)throws UserException;

}
