package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.dto.RegisterRequest;
import com.example.eindopdracht_backend_ipmroved.entity.User;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.InvalidDataException;
import com.example.eindopdracht_backend_ipmroved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            logger.error("Attempt to register with existing username: {}", registerRequest.getUsername());
            throw new InvalidDataException("Username already exists");
        }

        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            logger.error("Invalid user data: {}", registerRequest);
            throw new InvalidDataException("Invalid user data");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("User not found with username: {}", username);
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
