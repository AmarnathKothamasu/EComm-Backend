package com.springjwt.services.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.dto.ContactUsDTO;
import com.springjwt.entities.ContactUs;
import com.springjwt.entities.User;
import com.springjwt.repositories.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsRepository contactUsRepository;
	@Override
	public void saveContact(ContactUsDTO contactUsDTO) {
		ContactUs contactUs = new ContactUs();
		contactUs.setCountry(contactUsDTO.getCountry());
		contactUs.setEmail(contactUsDTO.getEmail());
		contactUs.setFirstName(contactUsDTO.getFirstName());
		contactUs.setLastName(contactUsDTO.getLastName());
		contactUs.setSubject(contactUsDTO.getSubject());
		contactUs.setUser(new User(contactUsDTO.getUserId()));
		contactUsRepository.save(contactUs);
	}
	@Override
	public List<ContactUs> getContact() {
		List<ContactUs> contactUsitems= contactUsRepository.findAll();
		return contactUsitems;
		
	}

}
