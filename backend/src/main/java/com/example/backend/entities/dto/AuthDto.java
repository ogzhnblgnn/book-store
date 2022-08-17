package com.example.backend.entities.dto;

import lombok.Data;

@Data
public class AuthDto {

    private int userId;
    private String message;
    private String userName;
    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
