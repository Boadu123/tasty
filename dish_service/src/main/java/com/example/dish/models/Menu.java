package com.example.dish.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Menu {

    public Menu() {
    }

    public Menu(UUID id, String name, String description, boolean isActive,
                LocalDateTime createdAt, LocalDateTime updatedAt, Set<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dishes = dishes != null ? dishes : new HashSet<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "menu" )
    private Set<Dish> dishes = new HashSet<>();

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public static class MenuBuilder {
        private UUID id;
        private String name;
        private String description;
        private boolean isActive;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<Dish> dishes;

        public MenuBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MenuBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MenuBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MenuBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public MenuBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MenuBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public MenuBuilder dishes(Set<Dish> dishes) {
            this.dishes = dishes;
            return this;
        }

        public Menu build() {
            return new Menu(id, name, description, isActive, createdAt, updatedAt, dishes);
        }
    }
}
