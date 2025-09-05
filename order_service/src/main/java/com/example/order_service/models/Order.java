package com.example.order_service.models;


import com.example.order_service.enums.OrderStatus;
import jakarta.persistence.*;
        import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    // No-args constructor
    public Order() {
    }

    // All-args constructor
    public Order(UUID id, UUID userId, BigDecimal totalPrice, OrderStatus status,
                 LocalDateTime createdAt, Set<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.orderItems = orderItems != null ? orderItems : new HashSet<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING) // store as String, not as number
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Set<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(Set<OrderItem> orderItems) { this.orderItems = orderItems; }

    // Builder
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private UUID id;
        private UUID userId;
        private BigDecimal totalPrice;
        private OrderStatus status;
        private LocalDateTime createdAt;
        private Set<OrderItem> orderItems;

        public OrderBuilder id(UUID id) { this.id = id; return this; }
        public OrderBuilder userId(UUID userId) { this.userId = userId; return this; }
        public OrderBuilder totalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; return this; }
        public OrderBuilder status(OrderStatus status) { this.status = status; return this; }
        public OrderBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OrderBuilder orderItems(Set<OrderItem> orderItems) { this.orderItems = orderItems; return this; }

        public Order build() {
            return new Order(id, userId, totalPrice, status, createdAt, orderItems);
        }
    }
}
