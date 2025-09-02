package com.example.dish.dto.request;

public record MenuUpdateDTO(
        String name,
        String description,
        Boolean isActive
) {
}
