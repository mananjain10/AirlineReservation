package com.lti.service;



import java.io.File;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private JavaMailSender jmailSender;
	public void sendEmailForNewRegistration(String email,String text,String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("mananjain3003@outlook.com");
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

	}
	
	public void sendEmailWithPicForNewRegistration(String email,String text1,String subject,String fileToAttach) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
	            mimeMessage.setFrom(new InternetAddress("mananjain3003@outlook.com"));
	            mimeMessage.setSubject(subject);
	            mimeMessage.setText(text1);
	             
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	             
	            helper.setText("<html><body><img src='cid:identifier1234'><br>You have successfully"
	            		+ " registered. We will give our best no matter what.</body></html>", true);
	             
	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
	            helper.addInline("identifier1234", res);
	        }
		};
		try {
	        jmailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }

	}
	
	
public void sendEmailWithTicketAttached(String email,String text1,String subject,String fileToAttach) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
	            mimeMessage.setFrom(new InternetAddress("mananjain3003@outlook.com"));
	            mimeMessage.setSubject(subject);
	            mimeMessage.setText(text1);
	             
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            	             
	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
	            helper.addAttachment("ticket.pdf", res);
	        }
		};
		try {
	        jmailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }

	}

public boolean sendEmailForForgotPassword(String email,String text,String subject) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("mananjain3003@outlook.com");
	message.setTo(email);
	message.setSubject(subject);
	message.setText(text);
	mailSender.send(message);
	return true;
}
public void sendEmailForFeedback(String email,String text,String subject) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("mananjain3003@outlook.com");
	message.setTo(email);
	message.setSubject(subject);
	message.setText(text);
	mailSender.send(message);

}

public void sendEmailForNewContactUs(String email,String text,String subject) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("mananjain3003@outlook.com");
	message.setTo(email);
	message.setSubject(subject);
	message.setText(text);
	mailSender.send(message);

}

public void sendEmailForRefund(String email,String text,String subject) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("mananjain3003@outlook.com");
	message.setTo(email);
	message.setSubject(subject);
	message.setText(text);
	mailSender.send(message);

}

public void sendEmailForTicket(String email,String text,String subject) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("mananjain3003@outlook.com");
	message.setTo(email);
	message.setSubject(subject);
	message.setText(text);
	mailSender.send(message);

}

}
