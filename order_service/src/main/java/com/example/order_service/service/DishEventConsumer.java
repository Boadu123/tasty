package com.example.order_service.service;

import com.example.order_service.event.DishEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DishEventConsumer {

    private final DishCacheService dishCacheService;

    public DishEventConsumer(DishCacheService dishCacheService) {
        this.dishCacheService = dishCacheService;
    }

    @KafkaListener(topics = "dish-events", groupId = "order-service-group")
    public void handleDishEvent(DishEvent event) {
        System.out.println("Received dish event: " + event);

        switch (event.eventType()) {
            case CREATED, UPDATED:
                dishCacheService.updateCache(event.data());
                break;
            case DELETED:
                dishCacheService.removeFromCache(event.data().id());
                break;
        }
    }

    @KafkaListener(topics = "dish-snapshots", groupId = "order-service-group")
    public void handleSnapshotEvent(DishEvent event) {
        System.out.println("📥 Received dish snapshot: " + event);
        dishCacheService.updateCache(event.data());
    }
}
