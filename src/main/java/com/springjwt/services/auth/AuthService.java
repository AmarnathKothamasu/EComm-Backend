package com.springjwt.services.auth;

import com.springjwt.dto.CreatedUserDTO;
import com.springjwt.dto.RegisterUserVerification;
import com.springjwt.dto.SignupDTO;
import com.springjwt.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);

	String register(CreatedUserDTO createdUserDTO);

	String regenerateOtp(String email);

	String otpVerification(RegisterUserVerification registerUserVerification);
}
