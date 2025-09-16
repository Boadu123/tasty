package com.example.order_service.service.impl;

import com.example.order_service.dto.request.OrderRequestDTO;
import com.example.order_service.dto.response.DishResponse;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.models.Order;
import com.example.order_service.models.OrderItem;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.DishCacheService;
import com.example.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

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
}
