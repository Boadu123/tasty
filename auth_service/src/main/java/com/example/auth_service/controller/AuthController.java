package com.example.auth_service.controller;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;
import com.example.auth_service.dto.response.UserResponseDTO;
import com.example.auth_service.mapper.UserResgisterMapper;
import com.example.auth_service.models.User;
import com.example.auth_service.service.imp.AuthServiceImp;
import com.example.auth_service.service.imp.UserServiceImp;
import com.example.auth_service.utils.ApiResponse;
import com.example.auth_service.utils.ApiSuccessResponse;
import jakarta.validation.Valid;
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
    private final UserServiceImp userServiceImp;

    public AuthController(AuthServiceImp authServiceImp, UserServiceImp userServiceImp) {
        this.authServiceImp = authServiceImp;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO loginResponseDTO = authServiceImp.login(loginRequestDTO);

        ApiResponse<LoginResponseDTO> apiResponse = ApiSuccessResponse.buildSuccessResponse(
                HttpStatus.OK,
                "Logged in successfully",
                loginResponseDTO
        );

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = authServiceImp.registerUser(userRegisterRequestDTO);

        UserResponseDTO responseDTO = UserResgisterMapper.toUserRegisterResponseDTO(user);

        ApiResponse<UserResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setCode(HttpStatus.CREATED.value());
        apiResponse.setMessage("User registered successfully");
        apiResponse.setData(responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
