package com.example.dish.controller;

import com.example.dish.dto.request.MenuRequestDTO;
import com.example.dish.dto.response.MenuResponseDTO;
import com.example.dish.service.MenuService;
import com.example.dish.utils.ApiResponse;
import com.example.dish.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

        private final MenuService menuService;

        public MenuController(MenuService menuService) {
            this.menuService = menuService;
        }

        @PostMapping
        public ResponseEntity<ApiResponse<MenuResponseDTO>> createMenu(@RequestBody @Valid MenuRequestDTO menuRequestDTO){

            MenuResponseDTO menuResponseDTO = menuService.createMenu(menuRequestDTO);

            ApiResponse<MenuResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                    HttpStatus.CREATED,
                    "Menu Created Successfully",
                    menuResponseDTO
            );

            return new  ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<ApiResponse<Page<MenuResponseDTO>>> getAllMenus(Pageable pageable){
            Page<MenuResponseDTO> menus = menuService.getAllMenus(pageable);

            ApiResponse<Page<MenuResponseDTO>> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                    HttpStatus.OK,
                    "Menus Retrieved Successfully",
                    menus
            );

            return new  ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
}
