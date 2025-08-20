package com.example.auth_service.dto.request;

import com.example.auth_service.enums.Role;
import jakarta.validation.constraints.*;

public record UserUpdateRequestDTO(
        String firstName,

        String middleName,

        String lastName,

        String email,

        String password,

        Role role

) {

}
