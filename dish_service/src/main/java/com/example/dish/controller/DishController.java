package com.example.dish.controller;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.response.DishResponseDTO;
import com.example.dish.service.impl.DishServiceImpl;
import com.example.dish.utils.ApiResponse;
import com.example.dish.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(("/api/v1/dish"))
public class DishController {

    private final DishServiceImpl dishServiceImpl;

    public DishController(DishServiceImpl dishServiceImpl) {
        this.dishServiceImpl = dishServiceImpl ;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DishResponseDTO>> createDish(@RequestBody @Valid DishRequestDTO dishRequestDTO) {

        DishResponseDTO createdDish = dishServiceImpl.createDish(dishRequestDTO);

        ApiResponse<DishResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.CREATED,
                "Dish Created Successfully",
                createdDish
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DishResponseDTO>>> getAllDishes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            Pageable pageable) {

        Page<DishResponseDTO> dishes = dishServiceImpl.getAllDishes(pageable);

        ApiResponse<Page<DishResponseDTO>> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Dishes Retrieved Successfully",
                dishes
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DishResponseDTO>> getDishById(@PathVariable UUID id) {
        DishResponseDTO dishResponseDTO = dishServiceImpl.getDishById(id);

        ApiResponse<DishResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Dish Retrieved Successfully",
                dishResponseDTO
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
