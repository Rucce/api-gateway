package com.project.api_gateway.service;

import com.project.api_gateway.feign.AuthApiClient;
import com.project.apigateway.model.UserResponse;
import com.project.userservice.model.LoginRequest;
import com.project.userservice.model.LoginResponse;
import com.project.userservice.model.RegisterRequest;
import com.project.userservice.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthApiClient authApiClient;

    public UserResponse getUserByEmail(String email) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String roleClaim = jwt.getClaim("role");
        String subject = jwt.getSubject();
        if ("user".equals(roleClaim) && email.equals(subject)) {
            return stubUserByEmail();
        }
        throw new RuntimeException("Jwt not Valid!");
    }

    private UserResponse stubUserByEmail() {
        return new UserResponse()
                .name("Mario")
                .surname("Rossi")
                .role("user")
                .email("mario.rossi@gmail.com");
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return authApiClient.login(loginRequest);
    }


    public RegisterResponse register(RegisterRequest registerRequest) {
        return authApiClient.register(registerRequest);
    }
}