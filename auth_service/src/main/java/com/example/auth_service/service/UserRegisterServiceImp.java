package com.example.auth_service.service;

import com.example.auth_service.dto.request.UserRegisterRequestDTO;
import com.example.auth_service.exception.DuplicateResourceException;
import com.example.auth_service.mapper.UserResgisterMapper;
import com.example.auth_service.models.User;
import com.example.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImp implements UserServiceRegister{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        if(userRepository.existsByEmail(userRegisterRequestDTO.email())){
            throw new DuplicateResourceException("Email already Exist");
        }

        User userRegister = UserResgisterMapper.toUserRegisterModel(userRegisterRequestDTO);
        userRegister.setPassword(passwordEncoder.encode(userRegisterRequestDTO.password()));
        userRepository.save(userRegister);

        return userRegister;
    }
}
