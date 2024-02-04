package com.springjwt.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	  @Autowired
	  private JavaMailSender javaMailSender;

	  public void sendOtpEmail(String email, String otp) throws MessagingException {
//		JavaMailSender javaMailSender = getJavaMailSender();
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
	    mimeMessageHelper.setTo(email);
	    mimeMessageHelper.setSubject("Verify OTP");
	    mimeMessageHelper.setText("The Otp for you account is: "+otp);

	    javaMailSender.send(mimeMessage);
	  }
	  
//	    private static JavaMailSender getJavaMailSender() {
//	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	        mailSender.setHost("smtp.gmail.com");
//	        mailSender.setPort(587);
//
//	        mailSender.setUsername("amu9118@gmail.com");
//	        mailSender.setPassword("Jaya@2022");
//
//	        Properties props = mailSender.getJavaMailProperties();
//	        props.put("mail.transport.protocol", "smtp");
//	        props.put("mail.smtp.auth", "true");
//	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.debug", "true");
//
//	        return mailSender;
//	    }
}
