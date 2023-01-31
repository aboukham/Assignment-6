package com.example.Assignment6.service;

import com.example.Assignment6.entity.dto.request.LoginRequest;
import com.example.Assignment6.entity.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    ResponseEntity<?> refreshToken(String requestRefreshToken);
}
