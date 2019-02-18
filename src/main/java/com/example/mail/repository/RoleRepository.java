package com.example.mail.repository;

import com.example.mail.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRolename(String role);
}
