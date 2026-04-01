package com.project.api_gateway.controller;

import com.project.api_gateway.mapper.Mapper;
import com.project.api_gateway.service.UserService;
import com.project.apigateway.api.AuthApi;
import com.project.apigateway.model.LoginRequest;
import com.project.apigateway.model.LoginResponse;
import com.project.apigateway.model.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    @Autowired
    private UserService userService;

    private Mapper mapper;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        com.project.userservice.model.LoginResponse loginResponse
                = userService.login(mapper.mapLoginRequest(loginRequest));

        return ResponseEntity.ok().body(mapper.mapLoginResponse(loginResponse));

    }

    @Override
    public ResponseEntity<RegisterResponse> register(com.project.apigateway.model.RegisterRequest registerRequest) {
        com.project.userservice.model.RegisterResponse registerResponse
                = userService.register(mapper.mapRegisterRequest(registerRequest));

        return ResponseEntity.ok().body(mapper.mapRegisterResponse(registerResponse));
    }
}