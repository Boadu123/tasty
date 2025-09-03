package com.example.order_service.models;

import jakarta.persistence.*;

        import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    // No-args constructor
    public OrderItem() {
    }

    // All-args constructor
    public OrderItem(UUID id, Order order, UUID dishId, String dishName,
                     BigDecimal priceAtOrder, int quantity) {
        this.id = id;
        this.order = order;
        this.dishId = dishId;
        this.dishName = dishName;
        this.priceAtOrder = priceAtOrder;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "dish_name", nullable = false)
    private String dishName;

    @Column(name = "price_at_order", nullable = false)
    private BigDecimal priceAtOrder;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public UUID getDishId() { return dishId; }
    public void setDishId(UUID dishId) { this.dishId = dishId; }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public BigDecimal getPriceAtOrder() { return priceAtOrder; }
    public void setPriceAtOrder(BigDecimal priceAtOrder) { this.priceAtOrder = priceAtOrder; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Builder
    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }

    public static class OrderItemBuilder {
        private UUID id;
        private Order order;
        private UUID dishId;
        private String dishName;
        private BigDecimal priceAtOrder;
        private int quantity;

        public OrderItemBuilder id(UUID id) { this.id = id; return this; }
        public OrderItemBuilder order(Order order) { this.order = order; return this; }
        public OrderItemBuilder dishId(UUID dishId) { this.dishId = dishId; return this; }
        public OrderItemBuilder dishName(String dishName) { this.dishName = dishName; return this; }
        public OrderItemBuilder priceAtOrder(BigDecimal priceAtOrder) { this.priceAtOrder = priceAtOrder; return this; }
        public OrderItemBuilder quantity(int quantity) { this.quantity = quantity; return this; }

        public OrderItem build() {
            return new OrderItem(id, order, dishId, dishName, priceAtOrder, quantity);
        }
    }
}
