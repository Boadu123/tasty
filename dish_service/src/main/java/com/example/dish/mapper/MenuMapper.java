package com.example.dish.mapper;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.response.MenuResponseDTO;
import com.example.dish.models.Menu;

import java.util.stream.Collectors;

public class MenuMapper {
    public static Menu toMenuEntity(MenuRequestDTO menuRequestDTO) {
        if (menuRequestDTO == null) {
            return null;
        }

        return Menu.builder()
                .name(menuRequestDTO.name())
                .description(menuRequestDTO.description())
                .isActive(menuRequestDTO.isActive())
                .build();
    }

    public static MenuResponseDTO toMenuResponseDTO(Menu menu) {
        if (menu == null) {
            return null;
        }

        return new  MenuResponseDTO(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.isActive(),
                menu.getCreatedAt(),
                menu.getUpdatedAt(),
                menu.getDishes() != null ?
                        menu.getDishes().stream()
                                .map(dish -> new MenuResponseDTO.DishInfo(
                                        dish.getId(),
                                        dish.getName(),
                                        dish.getDescription(),
                                        dish.getPrice(),
                                        dish.getImage_url(),
                                        dish.isAvailable()
                                ))
                                .collect(Collectors.toSet())
                        : null
        );
    }
}
