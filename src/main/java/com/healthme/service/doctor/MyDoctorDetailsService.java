package com.healthme.service.doctor;

import com.healthme.model.entity.Doctor;
import com.healthme.model.MyUserPrincipal;
import com.healthme.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyDoctorDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(userName);
        if (doctor == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new MyUserPrincipal(doctor);
    }
}
