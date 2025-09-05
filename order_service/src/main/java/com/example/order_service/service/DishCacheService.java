package com.example.order_service.service;

import com.example.order_service.dto.response.DishResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DishCacheService {

    private final ConcurrentHashMap<UUID, DishResponse> cache = new ConcurrentHashMap<>();

    public DishResponse getFromCache(UUID id) {
        return cache.get(id);
    }

    public void updateCache(DishResponse dish) {
        cache.put(dish.id(), dish);
        System.out.println("Cache updated for dish: " + dish.id());
    }

    public void removeFromCache(UUID dishId) {
        cache.remove(dishId);
        System.out.println("Cache removed for dish: " + dishId);
    }
}
