package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.User;
import com.example.eindopdracht_backend_ipmroved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById((long) id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById((long) id);
    }
}
