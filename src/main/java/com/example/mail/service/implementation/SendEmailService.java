package com.example.mail.service.implementation;

import com.example.mail.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 *
 * {@link SendEmailService} implemented a sendEmailService method to
 * send an email welcome to the user, after registering.
 * <br/>
 *
 * created by  eric.nyandwi on Feb,11/02/2019
 */

@Service
public class SendEmailService  implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);
    private final JavaMailSender emailSender;

    @Autowired
    public SendEmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    @Async
    public void sendEmail(String quizzUserEntity, String subject, String text, boolean isHtml) throws MessagingException {
        if (quizzUserEntity == null){
            throw new IllegalArgumentException("email user can not be null.");
        }

        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
            helper.setTo(quizzUserEntity);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            emailSender.send(message);
        } catch (Exception ex) {
            LOGGER.info("Email n'a pas été envoyé error =>" + ex.getMessage());
        }
    }
}
