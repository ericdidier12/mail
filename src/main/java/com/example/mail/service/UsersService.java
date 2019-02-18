package com.example.mail.service;

import com.example.mail.dto.UserDTO;
import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */
public interface UsersService {
    Users registerUser(UserDTO users);
    Users findByUserName(String username);
    void createVerificationToken(Users users, String token);
    VerificationToken getVerificationToken(String verificationToken);
    void enableRegisterUser(Users users);

}
