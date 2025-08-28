package com.example.dish.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record DishRequestDTO(
        @NotBlank(message = "Dish name is required")
        String name,

        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        BigDecimal price,

        String image_url,

        boolean isAvailable,

        @NotNull(message = "Menu ID is required")
        UUID menuId
) {

}
