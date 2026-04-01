package com.project.api_gateway.mapper;

import com.project.apigateway.model.LoginRequest;
import com.project.apigateway.model.LoginResponse;
import com.project.apigateway.model.RegisterRequest;
import com.project.apigateway.model.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {

    private Mapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new Mapper();
    }

    @Test
    public void testMapLoginRequest() {
        LoginRequest apiRequest = new LoginRequest();
        apiRequest.setEmail("test@example.com");
        apiRequest.setPassword("password");

        com.project.userservice.model.LoginRequest usRequest = mapper.mapLoginRequest(apiRequest);

        assertEquals(apiRequest.getEmail(), usRequest.getEmail());
        assertEquals(apiRequest.getPassword(), usRequest.getPassword());
    }

    @Test
    public void testMapLoginResponse() {
        com.project.userservice.model.LoginResponse usResponse = new com.project.userservice.model.LoginResponse();
        usResponse.setToken("jwt-token");
        usResponse.setUserId("1");

        LoginResponse apiResponse = mapper.mapLoginResponse(usResponse);

        assertEquals(usResponse.getToken(), apiResponse.getToken());
        assertEquals(usResponse.getUserId(), apiResponse.getUserId());
    }

    @Test
    public void testMapRegisterRequest() {
        RegisterRequest apiRequest = new RegisterRequest();
        apiRequest.setEmail("test@example.com");
        apiRequest.setPassword("password");
        apiRequest.setName("John");
        apiRequest.setSurname("Doe");

        com.project.userservice.model.RegisterRequest usRequest = mapper.mapRegisterRequest(apiRequest);

        assertEquals(apiRequest.getEmail(), usRequest.getEmail());
        assertEquals(apiRequest.getPassword(), usRequest.getPassword());
        assertEquals(apiRequest.getName(), usRequest.getName());
        assertEquals(apiRequest.getSurname(), usRequest.getSurname());
    }

    @Test
    public void testMapRegisterResponse() {
        com.project.userservice.model.RegisterResponse usResponse = new com.project.userservice.model.RegisterResponse();
        usResponse.setOutcome("OK");
        usResponse.setDescription("User registered");

        RegisterResponse apiResponse = mapper.mapRegisterResponse(usResponse);

        assertEquals(usResponse.getOutcome(), apiResponse.getOutcome());
        assertEquals(usResponse.getDescription(), apiResponse.getDescription());
    }
}