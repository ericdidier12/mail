package com.example.mail.service;

import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;

/**
 * created by  eric.nyandwi on Feb,12/02/2019
 */
public interface EmailService {


    /**
     * sendEmailService allows you to send a welcome message to the user after registering
     *  <\br>
     *
     * @param quizzUserEntity type of object QuizzUserEntity
     * @param subject  type of  String  " it's the mail subject "
     * @param text   type of String     "
     * @throws MessagingException
     */
    void sendEmail(String quizzUserEntity, String subject, String text, boolean isHtml) throws MessagingException ;


}
