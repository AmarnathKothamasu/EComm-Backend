package com.springjwt.services.other;

import java.util.List;

import com.springjwt.entities.User;


public interface UserService {
	
	List<User> getAllUsers();

	User getUserById(Long UserId);
	
	String deleteUserById(Long UserId);

}
