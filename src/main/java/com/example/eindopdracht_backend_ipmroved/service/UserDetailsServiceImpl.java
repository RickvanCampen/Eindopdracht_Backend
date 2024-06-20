package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Gebruiker;
import com.example.eindopdracht_backend_ipmroved.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final GebruikerRepository gebruikerRepository;

    @Autowired
    public UserDetailsServiceImpl(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Gebruiker> gebruikerOptional = gebruikerRepository.findByGebruikersnaam(username);
        Gebruiker gebruiker = gebruikerOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(gebruiker.getGebruikersnaam(), gebruiker.getWachtwoord(), Collections.emptyList());
    }
}
