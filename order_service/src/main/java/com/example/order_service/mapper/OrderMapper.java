package com.example.order_service.mapper;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.models.Order;
import com.example.order_service.models.OrderItem;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toOrderEntity(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return null;
        }

        return Order.builder()
                .userId(orderRequestDTO.userId())
                .build();
    }

    public static OrderResponseDTO toOrderResponseDTO(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getOrderItems() != null
                        ? order.getOrderItems().stream()
                        .map(OrderItemMapper::toOrderItemResponseDTO)
                        .collect(Collectors.toSet())
                        : null
        );
    }
}
