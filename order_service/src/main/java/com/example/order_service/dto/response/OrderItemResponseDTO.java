package com.example.order_service.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(
        UUID id,
        UUID productId,
        int quantity,
        UUID orderId,
        String dishName,
        BigDecimal priceAtOrder
) {
}

