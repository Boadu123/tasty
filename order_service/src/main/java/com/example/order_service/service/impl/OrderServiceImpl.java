package com.example.order_service.service.impl;

import com.example.order_service.client.DishClient;
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
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final DishClient dishClient;
    private final DishCacheService cacheService;

    public OrderServiceImpl(OrderRepository orderRepository, DishClient dishClient, DishCacheService cacheService) {
        this.orderRepository = orderRepository;
        this.dishClient = dishClient;
        this.cacheService = cacheService;
    }

    public OrderResponseDTO placeOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setUserId(request.userId());

        List<OrderItem> items = request.items().stream().map(itemRequest -> {
            DishResponse dish;
            try {
                //Using FeignClient to fetch from the dish_service
                DishResponse response = dishClient.getDishById(itemRequest.productId());
                dish = response.data();
                cacheService.updateCache(dish); // refresh cache
            } catch (Exception e) {
                // fallback if service is down
                dish = cacheService.getFromCache(itemRequest.productId());
            }

            if (dish == null) {
                throw new RuntimeException("Dish not available and not in cache");
            }

            // snapshot product details
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setDishId(itemRequest.productId());
            orderItem.setDishName(dish.name());
            orderItem.setPriceAtOrder(BigDecimal.valueOf((dish.price())));
            orderItem.setQuantity(itemRequest.quantity());
            return orderItem;
        }).toList();

        BigDecimal totalPrice = items.stream()
                .map(i -> i.getPriceAtOrder().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(new HashSet<>(items));
        order.setTotalPrice(totalPrice);

        order.setOrderItems(new HashSet<>(items));
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toOrderResponseDTO(savedOrder);
    }
}


