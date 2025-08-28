package com.example.dish.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dish")
public class Dish {

    // No-args constructor
    public Dish() {
    }

    // All-args constructor
    public Dish(UUID id, String name, String description, BigDecimal price,
                String image_url, boolean isAvailable, LocalDateTime createdAt,
                LocalDateTime updatedAt, Menu menu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.menu = menu;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image")
    private String image_url;

    @Column(name = "is_available")
    boolean isAvailable;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public static DishBuilder builder() {
        return new DishBuilder();
    }

    public static class DishBuilder {
        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private String image_url;
        private boolean isAvailable;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Menu menu;

        public DishBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public DishBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DishBuilder description(String description) {
            this.description = description;
            return this;
        }

        public DishBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public DishBuilder image_url(String image_url) {
            this.image_url = image_url;
            return this;
        }

        public DishBuilder isAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public DishBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DishBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public DishBuilder menu(@NotNull(message = "Menu ID is required") Menu menu) {
            this.menu = menu;
            return this;
        }

        public Dish build() {
            return new Dish(id, name, description, price, image_url, isAvailable,
                    createdAt, updatedAt, menu);
        }
    }
}
