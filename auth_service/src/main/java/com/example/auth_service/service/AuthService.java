package com.example.auth_service.service;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;
import com.example.auth_service.models.User;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    User registerUser(UserRegisterRequestDTO userRegisterRequestDTO);
}
