package com.springjwt.services.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.entities.User;
import com.springjwt.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User getUserById(Long UserId) {
		User user = userRepository.getById(UserId);
		return user;
	}

	@Override
	public String deleteUserById(Long UserId) {
		User user = userRepository.getById(UserId);
		if(user.getName()!=null) {
		userRepository.deleteById(UserId);
		return "User has been succesfully deleted";
		}
		return null;
	}
	
	

}
