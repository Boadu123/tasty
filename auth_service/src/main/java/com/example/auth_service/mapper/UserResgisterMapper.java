package com.example.auth_service.mapper;

import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.dto.response.UserRegisterResponseDTO;
import com.example.auth_service.models.User;

public class UserResgisterMapper {

    public static User toUserRegisterModel(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRegisterRequestDTO == null) {
            return null;
        }

        return User.builder()
                .firstName(userRegisterRequestDTO.firstName())
                .middleName(userRegisterRequestDTO.middleName())
                .lastName(userRegisterRequestDTO.lastName())
                .email(userRegisterRequestDTO.email())
                .role(userRegisterRequestDTO.role())
                .password(userRegisterRequestDTO.password())
                .build();
    }

    public static UserRegisterResponseDTO toUserRegisterResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserRegisterResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
