package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.RegisterRequest;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.meetgenie.backend.dto.ApiResponse;
import com.meetgenie.backend.exception.EmailAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Service
public class    UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));   // Bcrypt in Sprint 2
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return new ApiResponse(true, "User registered successfully!");
    }
}