package com.example.dish.service;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.response.DishResponseDTO;

public interface DishService {
    public DishResponseDTO createDish(DishRequestDTO dishRequestDTO);
}
