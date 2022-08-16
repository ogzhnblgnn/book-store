package com.example.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import static com.example.backend.security.ApplicationUserRole.*;
import com.example.backend.security.ApplicationUser;
import com.example.backend.services.Abstract.ApplicationUserDao;
import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Repository("fake")
public class FakeApplicationUserDaoManager implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser("admin",
                        passwordEncoder.encode("admin123"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser("user",
                        passwordEncoder.encode("user123"),
                        USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true));
        return applicationUsers;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
}
