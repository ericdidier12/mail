package com.example.mail.event;

import com.example.mail.entity.Users;
import com.example.mail.service.EmailService;
import com.example.mail.service.UsersService;
import com.example.mail.util.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.UUID;

//import org.springframework.mail.javamail.JavaMailSender;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

	@Autowired
	private  UsersService userService;

	@Qualifier("messageSource")
	@Autowired
	private MessageSource messages;

	@Autowired
	private EmailService emailService;
	
//	@Autowired
//	private JavaMailSender mailSender;
	
	@Autowired
	private MailSender mailSender;

	@Override
	public void onApplicationEvent(OnRegistrationSuccessEvent event) {
		this.confirmRegistration(event);
		
	}

	private void confirmRegistration(OnRegistrationSuccessEvent event) {
		Users user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.createVerificationToken(user,token);
		
		String recipient = user.getEmail();
		String subject = "Registration Confirmation";
        String url 
          = event.getAppUrl() + "/confirmRegistration?token=" + token;
        String message = messages.getMessage("message.registrationSuccessConfimationLink", null, event.getLocale());
         


		try {
			emailService.sendEmail(recipient,subject, Contents.subject(user.getUsername(),"http://localhost:8080" + url),true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		/*SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipient);
        email.setSubject(subject);
        email.setText(message + "http://localhost:8080" + url);
        System.out.println(url);
        mailSender.send(email);*/
		
	}
	
	
}
