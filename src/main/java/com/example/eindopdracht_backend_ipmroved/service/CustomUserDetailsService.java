package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Gebruiker;
import com.example.eindopdracht_backend_ipmroved.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final GebruikerRepository gebruikerRepository;

    @Autowired
    public CustomUserDetailsService(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
        Optional<Gebruiker> gebruikerOptional = gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
        Gebruiker gebruiker = gebruikerOptional.orElseThrow(() ->
                new UsernameNotFoundException("Gebruiker niet gevonden met gebruikersnaam: " + gebruikersnaam));

        return new org.springframework.security.core.userdetails.User(
                gebruiker.getGebruikersnaam(),
                gebruiker.getWachtwoord(),
                mapRolesToAuthorities(Collections.singletonList("ROLE_USER"))); // Pas de rol aan indien nodig
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
