package com.example.auth_service.service.imp;

import com.example.auth_service.dto.request.LoginRequestDTO;
import com.example.auth_service.dto.response.LoginResponseDTO;
import com.example.auth_service.exception.UserNameNotFoundException;
import com.example.auth_service.mapper.LoginMapper;
import com.example.auth_service.models.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtService;
import com.example.auth_service.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImp(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.password()
                )
        );

        User user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new UserNameNotFoundException("User Not found"));

        String token = jwtService.generateToken(user.getEmail(), user.getId());
        return LoginMapper.toLoginResponseDTO(user , token);
    }


}
