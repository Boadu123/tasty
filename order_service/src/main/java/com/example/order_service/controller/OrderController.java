package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.request.OrderStatusUpdateDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.service.impl.OrderServiceImpl;
import com.example.order_service.utils.ApiResponse;
import com.example.order_service.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt")));
        Page<OrderResponseDTO> orders = orderServiceImpl.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderResponseDTO> orders = orderServiceImpl.getOrdersByStatus(status);

        ApiResponse<List<OrderResponseDTO>> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Orders Retrieved Successfully with status: " + status,
                orders
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(@PathVariable UUID id) {
        OrderResponseDTO order = orderServiceImpl.getOrderById(id);

        ApiResponse<OrderResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Order Retrieved Successfully",
                order
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> updateOrderStatus(
            @PathVariable UUID id,
            @RequestBody @Valid OrderStatusUpdateDTO statusUpdateDTO) {

        OrderResponseDTO updatedOrder = orderServiceImpl.updateOrderStatus(id, statusUpdateDTO);

        ApiResponse<OrderResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Order Status Updated Successfully",
                updatedOrder
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable UUID id) {
        orderServiceImpl.deleteOrder(id);

        ApiResponse<Void> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.NO_CONTENT,
                "Order Deleted Successfully",
                null
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

}
