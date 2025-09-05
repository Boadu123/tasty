package com.example.order_service.client;

import com.example.order_service.dto.response.DishResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "dish-service", url = "http://localhost:8081/api/v1/dish")
public interface DishClient {

    @GetMapping("/{id}")
    DishResponse getDishById(@PathVariable UUID id);


}
