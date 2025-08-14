package com.example.auth_service.dto.request;

import com.example.auth_service.enums.Role;
import jakarta.validation.constraints.*;

public record UserRegisterRequestDTO(

        @NotBlank(message = "First Name can't be blank")
        String firstName,

        String middleName,

        @NotBlank(message = "Last Name can't be blank")
        String lastName,

        @Email(message = "Invalid Email format")
        @NotBlank(message = "Email can't be blank")
        String email,

        @NotBlank(message = "Password can't be blank")
        @Size(min = 8, max= 64, message = "Password must be between 8 and 64 characters")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&\\.]{8,64}$",
                message = "Password must contain at least one Uppercase, one lowercase, one number and special character"
        )
        String password,

        @NotNull(message = "message must not be blank")
        Role role
) {}
