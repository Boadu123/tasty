package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OrderUpdateDTO(
        @NotBlank(message = "Order status is required")
        String status
) {
}

