package com.springjwt.services.other;

import java.util.List;

import com.springjwt.dto.ContactUsDTO;
import com.springjwt.entities.ContactUs;

public interface ContactUsService {

	void saveContact(ContactUsDTO contactUsDTO);

	List<ContactUs> getContact();

}
