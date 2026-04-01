package com.project.api_gateway.service;

import com.project.userservice.api.AuthApi;
import com.project.userservice.model.LoginRequest;
import com.project.userservice.model.LoginResponse;
import com.project.userservice.model.RegisterRequest;
import com.project.userservice.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements AuthApi {

    @Autowired
    private AuthApi authApi;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return authApi.login(loginRequest);
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest) {
        return authApi.register(registerRequest);
    }
}