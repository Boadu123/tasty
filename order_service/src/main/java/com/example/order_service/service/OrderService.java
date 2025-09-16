package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.request.OrderStatusUpdateDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO request);
    public Page<OrderResponseDTO> getAllOrders(Pageable pageable);
    List<OrderResponseDTO> getOrdersByStatus(OrderStatus status);
    void deleteOrder(UUID orderId);
    OrderResponseDTO updateOrderStatus(UUID orderId, OrderStatus status);
    OrderResponseDTO getOrderById(UUID orderId);
    OrderResponseDTO updateOrderStatus(UUID orderId, OrderStatusUpdateDTO statusUpdateDTO);
}
