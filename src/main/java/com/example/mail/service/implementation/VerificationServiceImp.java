package com.example.mail.service.implementation;

import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;
import com.example.mail.repository.VerificationRepository;
import com.example.mail.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */

@Service
@Transactional
public class VerificationServiceImp implements VerificationService {

    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationServiceImp(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public VerificationToken findByToken(String token) {
      return verificationRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUsers(Users users) {
        return verificationRepository.findByUsers(users);
    }

    @Override
    public void saveV(VerificationToken verificationToken) {
        VerificationToken  verificationToken1 = verificationRepository.save(verificationToken);
    }
}
