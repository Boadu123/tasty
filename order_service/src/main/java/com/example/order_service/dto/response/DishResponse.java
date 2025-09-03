package com.example.order_service.dto.response;

import java.util.UUID;

public record DishResponse(String status, UUID id, String message, DishResponse data, double price, String name) {

}

