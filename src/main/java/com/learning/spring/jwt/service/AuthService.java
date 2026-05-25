package com.learning.spring.jwt.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.spring.jwt.dto.LoginRequest;
import com.learning.spring.jwt.dto.RegisterRequest;
import com.learning.spring.jwt.entity.User;
import com.learning.spring.jwt.repository.UserRepository;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            return "Invalid password";
        }

        return jwtService.generateToken(user.getEmail());
    }

	

	
}