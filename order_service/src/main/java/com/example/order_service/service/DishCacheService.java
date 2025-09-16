package com.example.order_service.service;

import com.example.order_service.dto.response.DishResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DishCacheService {

    @Cacheable(value = "dish", key = "#productId")
    public DishResponse getFromCache(UUID productId) {
        return null; // Spring will return null if not in cache
    }

    // Store/Update dish in cache
    @CachePut(value = "dish", key = "#dish.id()")
    public DishResponse updateCache(DishResponse dish) {
        System.out.println("Caching dish: " + dish);
        return dish;
    }

    // Evict dish from cache
    @CacheEvict(value = "dish", key = "#productId")
    public void removeFromCache(UUID productId) {}
}
