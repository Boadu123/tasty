package com.example.order_service.repository;

import com.example.order_service.models.OrderItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @EntityGraph(attributePaths = {"order", "dish"}) // fetch order item with related order and dish
    Optional<OrderItem> findById(UUID id);

    @EntityGraph(attributePaths = {"order", "dish"})
    List<OrderItem> findAll();
}

