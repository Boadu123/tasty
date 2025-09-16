package com.example.order_service.service.impl;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.request.OrderStatusUpdateDTO;
import com.example.order_service.dto.response.DishResponse;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.models.Order;
import com.example.order_service.models.OrderItem;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.DishCacheService;
import com.example.order_service.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishCacheService cacheService;

    public OrderServiceImpl(OrderRepository orderRepository, DishCacheService cacheService) {
        this.orderRepository = orderRepository;
        this.cacheService = cacheService;
    }

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setUserId(request.userId());

        List<OrderItem> items = request.items().stream().map(itemRequest -> {
            // 🔑 Now we depend only on the cache populated by Kafka events
            DishResponse dish = cacheService.getFromCache(itemRequest.productId());

            if (dish == null || !dish.isAvailable()) {
                throw new RuntimeException("Dish not available: " + itemRequest.productId());
            }

            // snapshot product details
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setDishId(itemRequest.productId());
            orderItem.setDishName(dish.name());
            orderItem.setPriceAtOrder(dish.price());
            orderItem.setQuantity(itemRequest.quantity());
            return orderItem;
        }).toList();

        BigDecimal totalPrice = items.stream()
                .map(i -> i.getPriceAtOrder().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(new HashSet<>(items));
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponseDTO(savedOrder);
    }

    @Override
    public Page<OrderResponseDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(OrderMapper::toOrderResponseDTO);
    }


    @Override
    public List<OrderResponseDTO> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(OrderMapper::toOrderResponseDTO)
                .toList();
    }

    @Override
    public OrderResponseDTO updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponseDTO(updatedOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return OrderMapper.toOrderResponseDTO(order);
    }

    @Override
    public OrderResponseDTO updateOrderStatus(UUID orderId, OrderStatusUpdateDTO statusUpdateDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        order.setStatus(statusUpdateDTO.status());
        Order updatedOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponseDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        orderRepository.delete(order);
    }



}
