package com.meetgenie.backend.service;

import com.meetgenie.backend.dto.RegisterRequest;
import com.meetgenie.backend.entity.User;
import com.meetgenie.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.meetgenie.backend.dto.ApiResponse;
import com.meetgenie.backend.exception.EmailAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.meetgenie.backend.dto.LoginRequest;
import com.meetgenie.backend.exception.InvalidCredentialsException;
import com.meetgenie.backend.dto.LoginResponse;

import java.time.LocalDateTime;

@Service
public class    UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(
                true,
                "Login successful",
                token
        );

    }
}