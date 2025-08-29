package com.example.dish.service;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.response.MenuResponseDTO;

public interface MenuService {

    public MenuResponseDTO createMenu(MenuRequestDTO menuRequestDTO);
}
