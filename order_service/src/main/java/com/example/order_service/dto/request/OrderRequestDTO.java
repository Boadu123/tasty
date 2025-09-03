package com.example.order_service.dto.request;

import com.example.order_service.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        @NotNull(message = "Customer ID is required")
        UUID userId,

        @NotNull(message = "Order items are required")
        List<OrderItemRequestDTO> items
) {
}

