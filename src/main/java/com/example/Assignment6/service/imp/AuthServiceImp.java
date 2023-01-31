package com.example.Assignment6.service.imp;

import com.example.Assignment6.entity.RefreshToken;
import com.example.Assignment6.entity.User;
import com.example.Assignment6.entity.dto.request.LoginRequest;
import com.example.Assignment6.entity.dto.response.LoginResponse;
import com.example.Assignment6.entity.dto.response.RefreshTokenResponse;
import com.example.Assignment6.exception.RefreshTokenException;
import com.example.Assignment6.service.AuthService;
import com.example.Assignment6.service.RefreshTokenService;
import com.example.Assignment6.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    private final RefreshTokenService refreshTokenService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getEmail());

        final String accessToken = jwtUtil.generateAccessTokenFromUsername(loginRequest.getEmail());
        final String refreshToken = refreshTokenService.createRefreshTokenByEmail(loginRequest.getEmail()).getToken();
        var loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }

    @Override
    public ResponseEntity<?> refreshToken(String requestRefreshToken) {
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    final String accessToken = jwtUtil.generateAccessTokenFromUsername(((User) user).getEmail());
                    return ResponseEntity.ok(new RefreshTokenResponse(accessToken, requestRefreshToken));
                })
                .orElseThrow(() -> new RefreshTokenException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
