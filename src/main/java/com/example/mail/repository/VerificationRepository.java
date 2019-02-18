package com.example.mail.repository;

import com.example.mail.entity.Role;
import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */

@Repository
public interface VerificationRepository extends JpaRepository<VerificationToken,Long> {
        VerificationToken findByToken(String token);
        VerificationToken findByUsers(Users users);

}
