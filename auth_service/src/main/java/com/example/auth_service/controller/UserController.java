package com.example.auth_service.controller;

import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.dto.response.UserRegisterResponseDTO;
import com.example.auth_service.mapper.UserResgisterMapper;
import com.example.auth_service.models.User;
import com.example.auth_service.service.UserRegisterServiceImp;
import com.example.auth_service.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    private final UserRegisterServiceImp userRegisterServiceImp;

    public UserController(UserRegisterServiceImp userRegisterServiceImp) {
        this.userRegisterServiceImp = userRegisterServiceImp;
    }

    public ResponseEntity<ApiResponse<UserRegisterResponseDTO>> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = userRegisterServiceImp.registerUser(userRegisterRequestDTO);

        UserRegisterResponseDTO responseDTO = UserResgisterMapper.toUserRegisterResponseDTO(user);

        ApiResponse<UserRegisterResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setCode(HttpStatus.CREATED.value());
        apiResponse.setMessage("User registered successfully");
        apiResponse.setData(responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);    }

}
