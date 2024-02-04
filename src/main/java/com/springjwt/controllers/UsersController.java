package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.entities.User;
import com.springjwt.services.other.UserService;

@RequestMapping("api/users")
@RestController
public class UsersController {
	
	 @Autowired
	    private UserService userservice;
	 
	 @GetMapping("/getAll")
	    public ResponseEntity<?> getAllUsers(){
	    	List<User> users = userservice.getAllUsers();
	    	if (users == null) {
	    		return new ResponseEntity<>("No User found or Badrequest!!",HttpStatus.NOT_FOUND);
	    	}
	    	return new ResponseEntity<>(users,HttpStatus.OK);
	    }
	    
	    @GetMapping("/getById")
	    public ResponseEntity<?> getUserById(@RequestParam Long userId) {
	        if (userId == null) {
	            return new ResponseEntity<>("user ID cannot be null. Bad request.", HttpStatus.BAD_REQUEST);
	        }
	        User user = userservice.getUserById(userId);
	        if (user == null) {
	            return new ResponseEntity<>("User not found for ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/deleteById")
	    public ResponseEntity<?> deleteProductById(@RequestParam Long userId){
	        if (userId == null) {
	            return new ResponseEntity<>("User ID cannot be null. Bad request.", HttpStatus.BAD_REQUEST);
	        }
	        String response = userservice.deleteUserById(userId);
	        if (response == null) {
	            return new ResponseEntity<>("User not found for ID: " + userId, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

}
