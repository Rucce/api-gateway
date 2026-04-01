package com.project.api_gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api_gateway.mapper.Mapper;
import com.project.api_gateway.service.UserService;
import com.project.apigateway.model.LoginRequest;
import com.project.apigateway.model.LoginResponse;
import com.project.apigateway.model.RegisterRequest;
import com.project.apigateway.model.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = AuthController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
@Import(Mapper.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Mapper mapper;

    @MockBean
    private UserService userService;

    @Test
    public void testRegister() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setName("John");

        com.project.userservice.model.RegisterResponse userResponse = new com.project.userservice.model.RegisterResponse();
        userResponse.setOutcome("OK");
        when(userService.register(any(com.project.userservice.model.RegisterRequest.class)))
                .thenReturn(userResponse);

        RegisterResponse apiResponse = mapper.mapRegisterResponse(userResponse);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(apiResponse)));
    }

    @Test
    public void testLogin() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        com.project.userservice.model.LoginResponse userResponse = new com.project.userservice.model.LoginResponse();
        userResponse.setToken("jwt-token-123");
        userResponse.setUserId("1");
        when(userService.login(any(com.project.userservice.model.LoginRequest.class)))
                .thenReturn(userResponse);

        LoginResponse apiResponse = mapper.mapLoginResponse(userResponse);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(apiResponse)));
    }
}