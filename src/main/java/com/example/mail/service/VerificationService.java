package com.example.mail.service;

import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */
public interface VerificationService {
    VerificationToken findByToken(String token);
    VerificationToken findByUsers(Users users);
    void saveV(VerificationToken verificationToken);

}
