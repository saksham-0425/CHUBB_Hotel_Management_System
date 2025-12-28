package com.hotel.auth_service.service;

import com.hotel.auth_service.dto.AuthResponse;
import com.hotel.auth_service.dto.LoginRequest;
import com.hotel.auth_service.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
