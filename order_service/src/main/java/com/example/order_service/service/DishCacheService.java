package com.example.order_service.service;

import com.example.order_service.dto.response.DishResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DishCacheService {

    private final Map<UUID, DishResponse> cache = new ConcurrentHashMap<>();

    public void updateCache(DishResponse dishResponse) {
        cache.put(dishResponse.id(), dishResponse);
    }

    public DishResponse getFromCache(UUID id) {
        return cache.get(id);
    }
}
