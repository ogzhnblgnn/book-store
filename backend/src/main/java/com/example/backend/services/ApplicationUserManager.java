package com.example.backend.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backend.services.Abstract.ApplicationUserDao;

@Service
public class ApplicationUserManager implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    public ApplicationUserManager(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .selectApplicationUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username could not find: " + username));
    }

}
