package com.example.Assignment6.service;

import com.example.Assignment6.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    public RefreshToken createRefreshTokenByEmail(String email);
    RefreshToken verifyExpiration(RefreshToken token);
    int deleteByUserId(Long userId);
}
