package com.example.backend.services;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.backend.entities.RefreshToken;
import com.example.backend.entities.User;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.repositories.RefreshTokenRepository;

@Service
public class RefreshTokenManager {
    
    @Value("${application.jwt.refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenManager(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public ApiResponse<String> createRefreshToken(User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if (token == null) {
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpireDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return ApiResponse.default_OK(token.getToken());
    }

    public ApiResponse<Boolean> isRefreshExpired(RefreshToken token) {
        Boolean isExpired = token.getExpireDate().before(new Date());
        return ApiResponse.default_OK(isExpired);
    }

    public ApiResponse<RefreshToken> getByUser(int userId) {
        return ApiResponse.default_OK(refreshTokenRepository.findByUserId(userId));
    }
}
