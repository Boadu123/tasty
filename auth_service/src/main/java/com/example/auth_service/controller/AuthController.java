package com.example.auth_service.controller;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;
import com.example.auth_service.service.AuthService;
import com.example.auth_service.service.imp.AuthServiceImp;
import com.example.auth_service.utils.ApiResponse;
import com.example.auth_service.utils.ApiSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImp authServiceImp;

    public AuthController(AuthServiceImp authServiceImp) {
        this.authServiceImp = authServiceImp;

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponseDTO = authServiceImp.login(loginRequestDTO);

        ApiResponse<LoginResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.CREATED,
                "Logged in successfully",
                loginResponseDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
