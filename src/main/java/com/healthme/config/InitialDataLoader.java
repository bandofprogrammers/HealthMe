package com.healthme.config;

import com.healthme.model.Role;
import com.healthme.model.User;
import com.healthme.repository.RoleRepository;
import com.healthme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ROLE_SUPER_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_PATIENT");
        createRoleIfNotFound("ROLE_DOCTOR");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test_Admin");
        user.setLastName("Test_Admin");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test_admin@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        Role patientRole = roleRepository.findByName("ROLE_PATIENT");
        user = new User();
        user.setFirstName("Test_User");
        user.setLastName("Test_User");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test_patient@test.com");
        user.setRoles(Arrays.asList(patientRole));
        user.setEnabled(true);
        userRepository.save(user);

        Role doctorRole = roleRepository.findByName("ROLE_DOCTOR");
        user = new User();
        user.setFirstName("Test_Doctor");
        user.setLastName("Test_Doctor");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test_doctor@test.com");
        user.setRoles(Arrays.asList(doctorRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(
            String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}