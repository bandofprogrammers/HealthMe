package com.healthme.service;

import com.healthme.model.MyUserPrincipal;
import com.healthme.model.Patient;
import com.healthme.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(userName);
        if (patient == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new MyUserPrincipal(patient);
    }
}
