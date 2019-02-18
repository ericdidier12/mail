package com.example.mail.service.implementation;

import com.example.mail.entity.Role;
import com.example.mail.entity.Users;
import com.example.mail.entity.VerificationToken;
import com.example.mail.repository.RoleRepository;
import com.example.mail.repository.UserRepository;
import com.example.mail.repository.VerificationRepository;
import com.example.mail.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * created by  eric.nyandwi on Feb,18/02/2019
 */

@Service
@Transactional
public class UserServiceImp implements UsersService {

    private final UserRepository  userRepository;
    private final VerificationRepository verificationRepository;
    private final PasswordEncoder passwordEncoder;
    private  final RoleRepository roleRepository ;

    @Autowired
    public UserServiceImp(UserRepository userRepository, VerificationRepository verificationRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public Users registerUser(Users users) {
        if(users == null){
            throw new IllegalArgumentException(" users ne peut pas être null ");
        }

        Users user = new Users();
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setUsername(users.getUsername());
        String hashedPassword = passwordEncoder.encode(users.getPassword());
        user.setPassword(hashedPassword);
        user.setEnabled(users.isEnabled());
        user.setEmail(users.getEmail());
        user.setRoles(Arrays.asList(this.createOrGetAuthority("ROLE_CANDIDATE")));
        return  userRepository.save(users);
    }


    private Role createOrGetAuthority(String authority) {

        Role found = roleRepository.findByRolename(authority);

        if (found == null) {
            found = new Role(authority);
            roleRepository.save(found);
        }
        return found;
    }

    @Override
    public Users findByUserName(String username) {
        return  userRepository.findUsersByUserName(username);
    }

    @Override
    public void createVerificationToken(Users users, String token) {
        VerificationToken newUserToken = new VerificationToken(token, users);
        verificationRepository.save(newUserToken);

    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationRepository.findByToken(verificationToken);
    }

    @Override
    public void enableRegisterUser(Users users) {
        userRepository.save(users);
    }
}
