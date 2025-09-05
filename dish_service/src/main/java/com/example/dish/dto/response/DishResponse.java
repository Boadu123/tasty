package com.example.dish.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record DishResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String image_url,
        boolean isAvailable) {

}
