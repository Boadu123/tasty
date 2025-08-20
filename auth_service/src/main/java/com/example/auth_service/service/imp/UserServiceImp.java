package com.example.auth_service.service.imp;

import com.example.auth_service.dto.request.UserUpdateRequestDTO;
import com.example.auth_service.dto.response.UserUpdateResponseDTO;
import com.example.auth_service.exception.UserNameNotFoundException;
import com.example.auth_service.models.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtService;
import com.example.auth_service.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNameNotFoundException("No authenticated user found");
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNameNotFoundException("User not found"));
    }

    public UserUpdateResponseDTO updateUserProfile(UserUpdateRequestDTO request) {
        User user = getUserProfile();

        // Optional updates if provided
        if (request.firstName() != null) user.setFirstName(request.firstName());
        if (request.middleName() != null) user.setMiddleName(request.middleName());
        if (request.lastName() != null) user.setLastName(request.lastName());
        if (request.email() != null) user.setEmail(request.email());
        if (request.password() != null) user.setPassword(passwordEncoder.encode(request.password()));
        if (request.role() != null) user.setRole(request.role());

        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);

        return new UserUpdateResponseDTO(
                updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getMiddleName(),
                updatedUser.getLastName(),
                updatedUser.getEmail(),
                updatedUser.getRole(),
                updatedUser.getCreatedAt(),
                updatedUser.getUpdatedAt()
        );
    }
}
