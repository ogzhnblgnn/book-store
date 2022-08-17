package com.example.backend.entities.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
