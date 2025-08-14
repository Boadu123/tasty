package com.example.auth_service.service;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
