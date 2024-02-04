package com.springjwt.controllers;

import com.springjwt.dto.CreatedUserDTO;
import com.springjwt.dto.RegisterUserVerification;
import com.springjwt.dto.SignupDTO;
import com.springjwt.dto.UserDTO;
import com.springjwt.services.auth.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

	@Autowired
	private AuthService authService;

	@PostMapping("/")
	public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
		UserDTO createdUser = authService.createUser(signupDTO);
		if (createdUser == null) {
			return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreatedUserDTO createdUserDTO) {
		return new ResponseEntity<>(authService.register(createdUserDTO), HttpStatus.OK);
	}

	@PutMapping("/regenerate-otp")
	public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
		return new ResponseEntity<>(authService.regenerateOtp(email), HttpStatus.OK);
	}

	@PostMapping("/otp-verification")
	public ResponseEntity<?> otpVerification(@RequestBody RegisterUserVerification registerUserVerification) {
		String response;
		try {
			response = authService.otpVerification(registerUserVerification);
		} catch (Exception e) {
			return new ResponseEntity<>("OTP not verified!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
