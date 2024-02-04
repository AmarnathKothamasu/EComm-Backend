package com.springjwt.services.auth;

import com.springjwt.dto.CreatedUserDTO;
import com.springjwt.dto.RegisterUserVerification;
import com.springjwt.dto.SignupDTO;
import com.springjwt.dto.UserDTO;
import com.springjwt.entities.CreatedUser;
import com.springjwt.entities.User;
import com.springjwt.repositories.CreatedUserRepository;
import com.springjwt.repositories.UserRepository;
import com.springjwt.util.EmailUtil;
import com.springjwt.util.EncryptionService;
import com.springjwt.util.OtpUtil;

import jakarta.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailUtil emailUtil;
    
    @Autowired
    private OtpUtil otpUtil;
    
    @Autowired
    private CreatedUserRepository createdUserRepository;
    
    @Autowired
    private EncryptionService encryptionService;
    
    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setuserId(createdUser.getuserId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        return userDTO;
    }

	public String register(CreatedUserDTO createdUserDTO) {
		Optional<CreatedUser> createdUserCheck = createdUserRepository.findByEmail(createdUserDTO.getEmail());
		if(!createdUserCheck.isEmpty()) {
			throw new RuntimeException("User with email already exists :" + createdUserDTO.getEmail());
		}
			
		String otp = otpUtil.generateOtp();
	    try {
	      emailUtil.sendOtpEmail(createdUserDTO.getEmail(), otp);
	    } catch (MessagingException e) {
	      throw new RuntimeException("Unable to send otp please try again");
	    }
	    CreatedUser createdUser = new CreatedUser();
	    createdUser.setName(createdUserDTO.getName());
	    createdUser.setEmail(createdUserDTO.getEmail());
	    try {
			createdUser.setPassword(new BCryptPasswordEncoder().encode(createdUserDTO.getPassword()));
		} catch (Exception e) {
			throw new RuntimeException("Unable to create user : "+e.getMessage());
		}
	    createdUser.setOtp(otp);
	    createdUser.setOtpGeneratedTime(LocalDateTime.now());
	    createdUser.setActive(false);
	    createdUserRepository.save(createdUser);
	    return "User registration successful";
	}

	public String regenerateOtp(String email) {
	    CreatedUser Createduser = createdUserRepository.findByEmail(email)
	        .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
	    String otp = otpUtil.generateOtp();
	    try {
	      emailUtil.sendOtpEmail(email, otp);
	    } catch (MessagingException e) {
	      throw new RuntimeException("Unable to send otp please try again");
	    }
	    Createduser.setOtp(otp);
	    Createduser.setOtpGeneratedTime(LocalDateTime.now());
	    createdUserRepository.save(Createduser);
	    return "Email sent... please verify account within 1 minute";
	  }

	@Override
	public String otpVerification(RegisterUserVerification registerUserVerification) {
		// TODO Auto-generated method stub
		//Updating the createdUser table
		 CreatedUser Createduser = createdUserRepository.findByEmail(registerUserVerification.getEmail())
			        .orElseThrow(() -> new RuntimeException("User not found with this email: " + registerUserVerification.getEmail()));
		  if(Createduser == null){
	            throw new UsernameNotFoundException("User not found",null);
	        }
		  if (Integer.parseInt(Createduser.getOtp())==Integer.parseInt(registerUserVerification.getOtp())) {
			  Createduser.setActive(true);
			  try {
			  createdUserRepository.save(Createduser);
			  }
			  catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  }
		  else {
			  throw new RuntimeException("Please enter the correct OTP");
		  }
		//Copying the user from createsUser table to user table for authentication
		  try {
	        User user = new User();
	        user.setName(Createduser.getName());
	        user.setEmail(Createduser.getEmail());
	        user.setPassword(Createduser.getPassword());
	        userRepository.save(user);
		  }
		  catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
		
		
		return "OTP Verified Successfully!!";
	}
}
