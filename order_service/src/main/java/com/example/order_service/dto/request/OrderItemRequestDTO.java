package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderItemRequestDTO(
        @NotNull(message = "Product ID is required")
        UUID productId,

        @Positive(message = "Quantity must be positive")
        int quantity,

        @NotNull(message = "Order ID is required")
        UUID orderId
) {
}

