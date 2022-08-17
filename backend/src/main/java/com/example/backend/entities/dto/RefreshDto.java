package com.example.backend.entities.dto;

import lombok.Data;

@Data
public class RefreshDto {
    private int userId;
    private String refreshToken;
}
