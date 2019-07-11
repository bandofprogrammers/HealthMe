package com.healthme.service.admin;

import com.healthme.model.entity.Admin;
import com.healthme.model.MyUserPrincipal;
import com.healthme.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(userName);
        if (admin == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new MyUserPrincipal(admin);
    }
}
