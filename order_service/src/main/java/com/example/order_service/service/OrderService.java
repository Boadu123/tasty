package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO request);
}
