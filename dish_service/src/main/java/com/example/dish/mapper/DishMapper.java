package com.example.dish.mapper;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.response.DishResponseDTO;
import com.example.dish.models.Dish;
import com.example.dish.models.Menu;

public class DishMapper {

    public static Dish toDishEntity(DishRequestDTO dishRequestDTO, Menu menu) {
        if (dishRequestDTO == null) {
            return null;
        }

        return Dish.builder()
                .name(dishRequestDTO.name())
                .description(dishRequestDTO.description())
                .image_url(dishRequestDTO.image_url())
                .price(dishRequestDTO.price())
                .isAvailable(dishRequestDTO.isAvailable())
                .menu(menu)
                .build();
    }

    public static DishResponseDTO toDishResponseDTO(Dish dish) {
        if (dish == null) {
            return null;
        }

        Menu menu = dish.getMenu();
        DishResponseDTO.MenuInfo menuInfo = null;

        if (menu != null) {
            menuInfo = new DishResponseDTO.MenuInfo(
                menu.getId(),
                menu.getName(),
                menu.isActive()
            );
        }

        return new DishResponseDTO(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getImage_url(),
                dish.isAvailable(),
                dish.getCreatedAt(),
                dish.getUpdatedAt(),
                menuInfo
        );
    }
}
