package com.example.dish.service.impl;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.response.DishResponseDTO;
import com.example.dish.exception.MenuExistException;
import com.example.dish.mapper.DishMapper;
import com.example.dish.models.Dish;
import com.example.dish.models.Menu;
import com.example.dish.repository.DishRepository;
import com.example.dish.repository.MenuRepository;
import com.example.dish.service.DishService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    public DishServiceImpl(DishRepository dishRepository,  MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    public DishResponseDTO createDish(DishRequestDTO dishRequestDTO){

        if(dishRequestDTO == null){
            return null;
        }

        Menu menu = menuRepository.findById(dishRequestDTO.menuId()).orElseThrow(() -> new MenuExistException("Menu with id " + dishRequestDTO.menuId() + " does not exist"));

        Dish dish = DishMapper.toDishEntity(dishRequestDTO, menu);
        dish.setCreatedAt(LocalDateTime.now());
        dish.setUpdatedAt(LocalDateTime.now());

        Dish savedDish = dishRepository.save(dish);

        return DishMapper.toDishResponseDTO(savedDish);
    }


}
