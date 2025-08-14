package com.example.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "Email cannot be Blank")
        String email,

        @NotBlank(message = "Password cannot be Blank")
        String password
) {
}
