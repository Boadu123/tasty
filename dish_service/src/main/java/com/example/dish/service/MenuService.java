package com.example.dish.service;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.request.MenuUpdateDTO;
import com.example.dish.dto.response.MenuResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MenuService {

    public MenuResponseDTO createMenu(MenuRequestDTO menuRequestDTO);

    public Page<MenuResponseDTO> getAllMenus(Pageable pageable);

    public MenuResponseDTO getMenuById(UUID id);

    public MenuResponseDTO updateMenu(UUID id, MenuUpdateDTO menuUpdateDTO);

    public void deleteMenu(UUID id);
}
