package com.example.auth_service.controller;

import com.example.auth_service.dto.response.UserResponseDTO;
import com.example.auth_service.models.User;
import com.example.auth_service.service.imp.UserServiceImp;
import com.example.auth_service.utils.ApiResponse;
import com.example.auth_service.utils.ApiSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getProfile() {
        User user = userServiceImp.getUserProfile();

        UserResponseDTO profileDTO = new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        ApiResponse<UserResponseDTO> response = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "User profile retrieved successfully",
                profileDTO
        );

        return ResponseEntity.ok(response);
    }
}
