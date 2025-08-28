package com.example.dish.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record MenuResponseDTO(
        UUID id,
        String name,
        String description,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<DishInfo> dishes
) {
    public record DishInfo(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            String imageUrl,
            boolean isAvailable
    ) {
    }
}
