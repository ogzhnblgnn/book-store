package com.example.backend.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.backend.entities.Role;
import com.example.backend.entities.User;
import com.example.backend.entities.dto.UserDto;
import com.example.backend.repositories.RoleRepository;
import com.example.backend.repositories.UserRepository;
import com.example.backend.security.ApplicationUser;
import com.example.backend.services.Abstract.UserService;

import lombok.RequiredArgsConstructor;

import static com.example.backend.security.ApplicationUserRole.*;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.exceptions.notFoundExceptions.UserNotFoundException;

@Service
@RequiredArgsConstructor
@Repository("mysql")
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {

        /// User
        User user = userRepository.findByUserName(username);

        // Role
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = new HashSet<>(); // ADMIN / EDITOR / USER
        roles = user.getRoles();
        // Bu uygulamada biz sadece 1 kayıt tutuyoruz. Ancak altyapı birden fazla role
        // tanımı yapmaya uygun!
        for (Role role : roles) {
            switch (role.getName()) { // role.getName()
                case "USER": // USER
                    grantedAuthorities.addAll(USER.getGrantedAuthorities());
                    break;
                case "ADMIN": // ADMIN
                    grantedAuthorities.addAll(ADMIN.getGrantedAuthorities());
                    break;
                default:
                    break;
            }
        }

        // ADMIN
        Optional<ApplicationUser> applicationUser = Optional
                .ofNullable(new ApplicationUser(username,
                        user.getPassword(),
                        grantedAuthorities, // Role tablosuna role al - enum ile birleştir
                        true,
                        true,
                        true,
                        true));

        return applicationUser;
    }

    @Override
    public ApiResponse<List<UserDto>> getAllUsers() {
        List<UserDto> list = userRepository
                .findAll()
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return ApiResponse.default_OK(list);
    }

    @Override
    public ApiResponse<UserDto> getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return ApiResponse.default_OK(mapper.map(user, UserDto.class));
    }

    @Override
    public ApiResponse<UserDto> createUser(User user) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("USER");
        if (role == null) {
            throw new RuntimeException("USER role is not defined.");
        }
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ApiResponse.default_CREATED(mapper.map(user, UserDto.class));
    }

    @Override
    public ApiResponse<UserDto> updateUser(int id, User user) {

        // Entity -> Dto
        UserDto userDto = getUserById(id).getData();

        // Dto -> Entity
        User userEntity = mapper.map(userDto, User.class);

        // The area to be updated
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        // Update roles
        Set<Role> roles = roleRepository.findByIdIn(user.getRoles());
        userEntity.setRoles(roles);

        userRepository.save(userEntity);

        // Entity -> Dto
        return ApiResponse.default_ACCEPTED(mapper.map(userEntity, UserDto.class));
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public ApiResponse<?> deleteUser(int id) {
        getUserById(id);
        userRepository.deleteById(id);
        return ApiResponse.default_NO_CONTENT();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("USER");
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

}
