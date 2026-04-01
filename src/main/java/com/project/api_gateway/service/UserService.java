package com.project.api_gateway.service;

import com.project.api_gateway.feign.AuthApiClient;
import com.project.userservice.model.LoginRequest;
import com.project.userservice.model.LoginResponse;
import com.project.userservice.model.RegisterRequest;
import com.project.userservice.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthApiClient authApiClient;

    public LoginResponse login(LoginRequest loginRequest) {
        return authApiClient.login(loginRequest);
    }


    public RegisterResponse register(RegisterRequest registerRequest) {
        return authApiClient.register(registerRequest);
    }
}