package com.project.api_gateway.model;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String ttl;
    private String errorDescription;

}
