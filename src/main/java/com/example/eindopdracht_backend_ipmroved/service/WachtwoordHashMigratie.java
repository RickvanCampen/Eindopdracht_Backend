package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.User;
import com.example.eindopdracht_backend_ipmroved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WachtwoordHashMigratie implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WachtwoordHashMigratie(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Iterable<User> users = repository.findAll();
        for (User user : users) {
            if (user.getPassword().startsWith("{noop}")) {
                String plaintextWachtwoord = user.getPassword().substring("{noop}".length());
                String gehashtWachtwoord = passwordEncoder.encode(plaintextWachtwoord);
                user.setPassword(gehashtWachtwoord);
//                repository.save(user);
            }
        }
    }

}
