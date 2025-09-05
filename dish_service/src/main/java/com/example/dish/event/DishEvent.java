package com.example.dish.event;


import com.example.dish.dto.response.DishResponse;

public record DishEvent(EventType eventType, DishResponse data ) {
    public enum EventType {
        CREATED,
        UPDATED,
        DELETED
    }
}
