package com.example.auth_service.dto.response;

import com.example.auth_service.enums.Role;

import java.time.LocalDateTime;

public record UserRegisterResponseDTO(
        String firstName,
        String middleName,
        String lastName,
        String email,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
