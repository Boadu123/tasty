package com.example.order_service.repository;

import com.example.order_service.dto.request.OrderStatusUpdateDTO;
import com.example.order_service.dto.response.OrderResponseDTO;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @EntityGraph(attributePaths = {"orderItems"}) // fetch order with its items and dishes
    Page<Order> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"orderItems"})
    Optional<Order> findById(UUID id);

    @EntityGraph(attributePaths = {"orderItems"})
    List<Order> findByStatus(OrderStatus status);


}

