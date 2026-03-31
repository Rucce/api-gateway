package com.project.api_gateway.controller;

import com.project.api_gateway.model.LoginRequest;
import com.project.api_gateway.model.LoginResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class AuthController {

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token = Jwts.builder()
                    .claim("role", "user")
                    .issuedAt(Date.from(Instant.now()))
                    .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .compact();

            LoginResponse response = new LoginResponse();
            response.setToken(token);
            response.setTtl("3600");
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse();
            response.setErrorDescription("Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }
}