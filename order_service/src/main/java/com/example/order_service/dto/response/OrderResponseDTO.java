package com.example.order_service.dto.response;

import com.example.order_service.enums.OrderStatus;

import java.util.Set;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        UUID customerId,
        OrderStatus status,
        Set<OrderItemResponseDTO> orderItems
) {
}

