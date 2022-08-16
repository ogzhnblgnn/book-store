package com.example.backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.backend.services.Abstract.ApplicationUserDao;

public class ApplicationUserManager implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    public ApplicationUserManager(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .selectApplicationUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username could not find: " + username));
    }

}
