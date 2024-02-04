package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.dto.ContactUsDTO;
import com.springjwt.entities.Cart;
import com.springjwt.entities.ContactUs;
import com.springjwt.services.other.ContactUsService;

@RestController
@RequestMapping("/api/contactUs")
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;

	@PostMapping("/save")
	public ResponseEntity<?> sendMessage(@RequestBody ContactUsDTO contactUsDTO) {
		try {
			contactUsService.saveContact(contactUsDTO);
			return ResponseEntity.ok("Message sent successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding message");

		}
	}
	
	@GetMapping("/getAllContactUsInfo")
	public ResponseEntity<?> getContactUsInfo(){
		try {
			List<ContactUs> contactUsItems =contactUsService.getContact();
			return new ResponseEntity<>(contactUsItems,HttpStatus.OK);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Retriving ContactUs");
		}
	}
	
	
}
