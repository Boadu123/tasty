package com.example.dish.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record DishResponseDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String image_url,
        boolean isAvailable,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        MenuInfo menu
) {
    public record MenuInfo(
            UUID id,
            String name,
            boolean isActive
    ) {}
}
