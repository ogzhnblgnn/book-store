package com.example.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entities.Role;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.repositories.RoleRepository;
import com.example.backend.services.Abstract.RoleService;

@Service
public class RoleManager implements RoleService {
    private final RoleRepository roleRepository;

    public RoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse<List<Role>> getAllRoles() {
        var roles = roleRepository.findAll();
        return ApiResponse.default_OK(roles);
    }

    @Override
    public ApiResponse<Role> createRole(Role role) {
        roleRepository.save(role);
        return ApiResponse.default_CREATED(role);
    }

}
