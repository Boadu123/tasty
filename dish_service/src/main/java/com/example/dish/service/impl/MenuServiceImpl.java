package com.example.dish.service.impl;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.response.MenuResponseDTO;
import com.example.dish.exception.MenuExistException;
import com.example.dish.mapper.MenuMapper;
import com.example.dish.models.Menu;
import com.example.dish.repository.MenuRepository;
import com.example.dish.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuResponseDTO createMenu(MenuRequestDTO menuRequestDTO) {

        if(menuRequestDTO == null){
            throw new IllegalArgumentException("menuRequestDTO is null");
        }

        if(menuRequestDTO.name().length() > 100){
            throw new IllegalArgumentException("name length cannot be greater than 100 characters");
        }

        boolean exist = menuRepository.existsByNameIgnoreCase(menuRequestDTO.name());
        if(exist){
            throw new MenuExistException("Menu with name " + menuRequestDTO.name() + " already exist");
        }

        Menu menu = MenuMapper.toMenuEntity(menuRequestDTO);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());

        Menu savedMenu = menuRepository.save(menu);
        return MenuMapper.toMenuResponseDTO(savedMenu);
    }

    public Page<MenuResponseDTO> getAllMenus(Pageable pageable) {
        Page<Menu> menuPage = menuRepository.findAll(pageable);
        return menuPage.map(MenuMapper::toMenuResponseDTO);
    }

    public MenuResponseDTO getMenuById(UUID id){
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuExistException("Menu with id " + id + " does not exist"));
        return MenuMapper.toMenuResponseDTO(menu);
    }
}
