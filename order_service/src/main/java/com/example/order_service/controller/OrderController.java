package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.service.impl.OrderServiceImpl;
import com.example.order_service.utils.ApiResponse;
import com.example.order_service.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> placeOrder(
            @RequestBody @Valid OrderRequestDTO orderRequestDTO) {

        // Call your service to place the order
        OrderResponseDTO createdOrder = orderServiceImpl.placeOrder(orderRequestDTO);

        // Wrap in ApiResponse
        ApiResponse<OrderResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.CREATED,
                "Order Placed Successfully",
                createdOrder
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
