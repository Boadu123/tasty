package com.example.order_service.event;

import com.example.order_service.dto.response.DishResponse;

public record DishEvent(EventType eventType, DishResponse data ) {
    public enum EventType {
        CREATED,
        UPDATED,
        DELETED,
        SNAPSHOT
    }
}
