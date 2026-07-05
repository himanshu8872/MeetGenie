package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.RegisterRequest;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.meetgenie.backend.dto.ApiResponse;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return new ApiResponse(false, "Email already exists!");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());   // Bcrypt in Sprint 2
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new ApiResponse(true, "User registered successfully!");
    }
}