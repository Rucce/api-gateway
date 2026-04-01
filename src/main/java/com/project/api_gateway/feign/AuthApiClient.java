package com.project.api_gateway.feign;

import com.project.userservice.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", url = "${userservice.url}")
public interface AuthApiClient extends AuthApi {
}