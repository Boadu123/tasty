package com.example.dish.service;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.response.MenuResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    public MenuResponseDTO createMenu(MenuRequestDTO menuRequestDTO);

    public Page<MenuResponseDTO> getAllMenus(Pageable pageable);
}
