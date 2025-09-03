package com.example.order_service.dto.request;

import jakarta.validation.constraints.Positive;

public record OrderItemUpdateDTO(
        @Positive(message = "Quantity must be positive")
        int quantity
) {
}

