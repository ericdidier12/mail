package com.example.mail.controller;

import com.example.mail.dto.UserDTO;
import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;
import com.example.mail.event.OnRegistrationSuccessEvent;
import com.example.mail.service.UsersService;
import com.example.mail.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */
@RestController
public class AccountController {

    private final Logger LOG = LoggerFactory.getLogger(AccountController.class);
    private final UsersService usersService ;
    private  final ApplicationEventPublisher eventPublisher;

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource messages;

    private final VerificationService verificationService;

    @Autowired
    public AccountController(UsersService usersService, ApplicationEventPublisher eventPublisher, VerificationService verificationService) {
        this.usersService = usersService;
        this.eventPublisher = eventPublisher;
        this.verificationService = verificationService;
    }



    @PostMapping("/registration")
    public Users registerNewUser(@RequestBody UserDTO userDTO ,  WebRequest request){
        Users registeredUser = new Users();
        String userName = userDTO.getUserName();

        registeredUser = usersService.findByUserName(userName);

        if(registeredUser!=null) {
            LOG.info("error","There is already an account with this username: " + userName);
            LOG.info("There is already an account with this username: " + userName);
            return registeredUser;
        }

        registeredUser = usersService.registerUser(userDTO);

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationSuccessEvent(registeredUser, request.getLocale(),appUrl));
        }catch(Exception re) {
            re.printStackTrace();
//			throw new Exception("Error while sending confirmation email");
        }
        return registeredUser;
    }



    @GetMapping("/confirmRegistration")
    public String confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        Locale locale=request.getLocale();
        VerificationToken verificationToken = usersService.getVerificationToken(token);
        if(verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            LOG.info("error" + message);
            return  message;
        }
        Users user = verificationToken.getUsers();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            LOG.info("message " + message);
            return message;
        }

        user.setEnabled(true);
        usersService.enableRegisterUser(user);
        return null;
    }





}
