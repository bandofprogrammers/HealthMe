package com.healthme.service;

import com.healthme.model.User;
import com.healthme.model.UserDto;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerNewUserAccount(UserDto accountDto) throws NullPointerException {

        if (emailExists(accountDto.getEmail())) {
            throw new NullPointerException("There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setEnabled(true);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_PATIENT")));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return true;
        }
        return false;
    }

}
