package com.example.auth_service.mapper;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;
import com.example.auth_service.models.User;
import org.aspectj.weaver.patterns.IToken;

public class LoginMapper {

    public static LoginResponseDTO toLoginResponseDTO(User user, String token) {
        if (user == null) {
            return null;
        }

        return new LoginResponseDTO(token, user.getId(),  user.getEmail());
    }
}
