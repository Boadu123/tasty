package com.example.auth_service.service;

import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.models.User;

public interface UserService {
    User getUserProfile();
}
