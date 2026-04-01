package com.project.api_gateway.service;

import com.project.api_gateway.feign.AuthApiClient;
import com.project.apigateway.model.UserResponse;
import com.project.userservice.model.LoginRequest;
import com.project.userservice.model.LoginResponse;
import com.project.userservice.model.RegisterRequest;
import com.project.userservice.model.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private AuthApiClient authApiClient;

    @MockBean
    private SecurityContext securityContext;

    @MockBean
    private Authentication authentication;

    @MockBean
    private Jwt jwt;

    @BeforeEach
    public void setUp() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(jwt);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testGetUserById_ValidJwt() {
        when(jwt.getClaim("role")).thenReturn("user");
        when(jwt.getClaim("userid")).thenReturn("1");

        UserResponse response = userService.getUserById("1");

        assertNotNull(response);
        assertEquals("Mario", response.getName());
        assertEquals("Rossi", response.getSurname());
        assertEquals("user", response.getRole());
        assertEquals("mario.rossi@gmail.com", response.getEmail());
    }

    @Test
    public void testGetUserById_InvalidJwt() {
        when(jwt.getClaim("role")).thenReturn("user");
        when(jwt.getClaim("userid")).thenReturn("2");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.getUserById("1");
        });

        assertEquals("Jwt not Valid!", exception.getMessage());
    }

    @Test
    public void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        LoginResponse mockResponse = new LoginResponse();
        mockResponse.setToken("jwt-token");
        mockResponse.setUserId("1");

        when(authApiClient.login(loginRequest)).thenReturn(mockResponse);

        LoginResponse response = userService.login(loginRequest);

        assertEquals("jwt-token", response.getToken());
        assertEquals("1", response.getUserId());
    }

    @Test
    public void testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password");
        registerRequest.setName("John");
        registerRequest.setSurname("Doe");

        RegisterResponse mockResponse = new RegisterResponse();
        mockResponse.setOutcome("OK");
        mockResponse.setDescription("User registered");

        when(authApiClient.register(registerRequest)).thenReturn(mockResponse);

        RegisterResponse response = userService.register(registerRequest);

        assertEquals("OK", response.getOutcome());
        assertEquals("User registered", response.getDescription());
    }
}