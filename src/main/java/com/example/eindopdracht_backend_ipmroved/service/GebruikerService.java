package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Gebruiker;
import com.example.eindopdracht_backend_ipmroved.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GebruikerService {

    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GebruikerService(GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Gebruiker findByGebruikersnaam(String gebruikersnaam) {
        return gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
    }

    public Gebruiker saveGebruiker(Gebruiker gebruiker) {
        gebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
        return gebruikerRepository.save(gebruiker);
    }
}
