package com.example.order_service.mapper;

import com.example.order_service.dto.request.OrderItemRequestDTO;
import com.example.order_service.dto.response.OrderItemResponseDTO;
import com.example.order_service.models.Order;
import com.example.order_service.models.OrderItem;

public class OrderItemMapper {

    public static OrderItem toOrderItemEntity(OrderItemRequestDTO orderItemRequestDTO, Order order) {
        if (orderItemRequestDTO == null) {
            return null;
        }

        return OrderItem.builder()
                .dishId(orderItemRequestDTO.productId())   // from request
                .quantity(orderItemRequestDTO.quantity())
                .order(order)   // parent relationship
                .build();
    }

    public static OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getDishId(),
                orderItem.getQuantity(),
                orderItem.getOrder() != null ? orderItem.getOrder().getId() : null,
                orderItem.getDishName(),
                orderItem.getPriceAtOrder()
                );
    }
}
