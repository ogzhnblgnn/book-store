package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.User;
import com.example.backend.entities.dto.UserDto;
import com.example.backend.entities.models.ApiResponse;

public interface UserService extends ApplicationUserDao {
    
    ApiResponse<List<UserDto>> getAllUsers();
    ApiResponse<UserDto> getUserById(int id);
    User getUserByUserName(String username);
    ApiResponse<UserDto> createUser(User user);
    User saveUser(User user);
    ApiResponse<UserDto> updateUser(int id, User user);
    ApiResponse<?> deleteUser(int id);
    
}
