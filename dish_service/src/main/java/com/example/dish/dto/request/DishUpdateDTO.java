package com.example.dish.dto.request;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record DishUpdateDTO(
        String name,
        String description,

        @Positive(message = "Price must be positive")
        BigDecimal price,

        String image_url,
        Boolean isAvailable,

        UUID menuId
) {
}
