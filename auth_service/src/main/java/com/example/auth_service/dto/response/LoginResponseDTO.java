package com.example.auth_service.dto.response;

import java.util.UUID;

public record LoginResponseDTO(
        String token,
        UUID id,
        String email
) {
}
