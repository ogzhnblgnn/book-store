package com.example.backend.services.Abstract;

import java.util.Optional;

import com.example.backend.security.ApplicationUser;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
