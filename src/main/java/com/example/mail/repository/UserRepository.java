package com.example.mail.repository;

import com.example.mail.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUsersByUserName(String userName);
}
