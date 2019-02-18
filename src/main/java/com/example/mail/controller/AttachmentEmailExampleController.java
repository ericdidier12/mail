package com.example.mail.controller;

import javax.mail.MessagingException;

import com.example.mail.configuration.MyConstants;
import com.example.mail.service.EmailService;
import com.example.mail.util.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttachmentEmailExampleController {


    private String  email = "eric.nyandwi@busi.eu";

    private final EmailService emailService ;
    private  final String body = "";

    @Autowired
    public AttachmentEmailExampleController(EmailService emailService) {
        this.emailService = emailService;
    }


    @ResponseBody
    @RequestMapping("/sendAttachmentEmail")
    public String sendAttachmentEmail() throws MessagingException {
        String applUrl = "https://support.avast.com/fr-be/article/Activate-Internet-Security";
        emailService.sendEmail(MyConstants.MY_EMAIL,"My fisrt email ", Contents.subject("Eric Nyandwi",applUrl), true);
        return "Email Sent!";
    }

}
