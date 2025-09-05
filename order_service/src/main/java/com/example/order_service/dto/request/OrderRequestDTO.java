package com.example.order_service.dto.request;

import com.example.order_service.dto.response.OrderItemResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        @NotNull(message = "Customer ID is required")
        UUID userId,

        @NotNull(message = "Order items are required")
        List<OrderItemRequestDTO> items
) {
}

