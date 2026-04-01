package com.project.api_gateway.controller;

import com.project.api_gateway.service.UserService;
import com.project.apigateway.api.UserApi;
import com.project.apigateway.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserResponse> getUserById(String userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }
}