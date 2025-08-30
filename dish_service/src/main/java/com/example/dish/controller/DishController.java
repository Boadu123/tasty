package com.example.dish.controller;

import com.example.dish.dto.request.DishRequestDTO;
import com.example.dish.dto.response.DishResponseDTO;
import com.example.dish.service.impl.DishServiceImpl;
import com.example.dish.utils.ApiResponse;
import com.example.dish.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
