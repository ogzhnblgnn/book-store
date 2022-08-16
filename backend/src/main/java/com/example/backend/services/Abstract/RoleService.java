package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Role;
import com.example.backend.entities.models.ApiResponse;

public interface RoleService {
    ApiResponse<List<Role>> getAllRoles();
    ApiResponse<Role> createRole(Role role);
}
