package com.example.Assignment6.controller;

import com.example.Assignment6.entity.dto.request.LoginRequest;
import com.example.Assignment6.entity.dto.request.RefreshTokenRequest;
import com.example.Assignment6.service.AuthService;
import com.example.Assignment6.service.RefreshTokenService;
import com.example.Assignment6.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
       return authService.refreshToken(request.getRefreshToken());
    }

}
