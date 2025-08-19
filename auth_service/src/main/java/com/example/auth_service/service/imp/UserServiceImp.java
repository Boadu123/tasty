package com.example.auth_service.service.imp;

import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.dto.response.UserResponseDTO;
import com.example.auth_service.exception.DuplicateResourceException;
import com.example.auth_service.exception.UserNameNotFoundException;
import com.example.auth_service.mapper.UserResgisterMapper;
import com.example.auth_service.models.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.CustomUserDetails;
import com.example.auth_service.security.JwtService;
import com.example.auth_service.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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


}
