package com.example.eindopdracht_backend_ipmroved.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.eindopdracht_backend_ipmroved.entity.Gebruiker;
import com.example.eindopdracht_backend_ipmroved.repository.GebruikerRepository;

@Component
public class WachtwoordHashMigratie implements CommandLineRunner {

    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WachtwoordHashMigratie(GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Iterable<Gebruiker> gebruikers = gebruikerRepository.findAll();
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getWachtwoord().startsWith("{noop}")) {
                String plaintextWachtwoord = gebruiker.getWachtwoord().substring("{noop}".length());
                String gehashtWachtwoord = passwordEncoder.encode(plaintextWachtwoord);
                gebruiker.setWachtwoord(gehashtWachtwoord);
                gebruikerRepository.save(gebruiker);
            }
        }
    }

}
