package com.project.api_gateway.mapper;

import com.project.apigateway.model.LoginRequest;
import com.project.apigateway.model.LoginResponse;
import com.project.apigateway.model.RegisterRequest;
import com.project.apigateway.model.RegisterResponse;

public class Mapper {

    public com.project.userservice.model.LoginRequest mapLoginRequest(LoginRequest apiRequest) {
        com.project.userservice.model.LoginRequest usRequest = new com.project.userservice.model.LoginRequest();
        usRequest.setEmail(apiRequest.getEmail());
        usRequest.setPassword(apiRequest.getPassword());
        return usRequest;
    }

    public LoginResponse mapLoginResponse(com.project.userservice.model.LoginResponse usResponse) {
        LoginResponse apiResponse = new LoginResponse();
        apiResponse.setToken(usResponse.getToken());
        return apiResponse;
    }

    public com.project.userservice.model.RegisterRequest mapRegisterRequest(RegisterRequest apiRequest) {
        com.project.userservice.model.RegisterRequest usRequest = new com.project.userservice.model.RegisterRequest();
        usRequest.setEmail(apiRequest.getEmail());
        usRequest.setPassword(apiRequest.getPassword());
        usRequest.setName(apiRequest.getName());
        usRequest.setSurname(apiRequest.getSurname());
        return usRequest;
    }

    public RegisterResponse mapRegisterResponse(com.project.userservice.model.RegisterResponse usResponse) {
        RegisterResponse apiResponse = new RegisterResponse();
        apiResponse.setDescription(usResponse.getDescription());
        apiResponse.setOutcome(usResponse.getOutcome());
        return apiResponse;
    }

}
