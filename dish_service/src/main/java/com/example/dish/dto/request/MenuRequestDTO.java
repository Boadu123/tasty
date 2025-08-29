package com.example.dish.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuRequestDTO(
        @NotBlank(message = "Menu name is required")
        String name,

        String description,

        @NotNull(message = "Active status is required")
        boolean isActive
) {
}

