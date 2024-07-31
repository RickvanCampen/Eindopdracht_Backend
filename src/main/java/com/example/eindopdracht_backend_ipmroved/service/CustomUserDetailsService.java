package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.MedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MedewerkerRepository medewerkerRepository;

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
        Medewerker medewerker = medewerkerRepository.findByGebruikersnaam(gebruikersnaam);
        if (medewerker == null) {
            throw new ResourceNotFoundException("Medewerker", "gebruikersnaam", gebruikersnaam);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(medewerker.getGebruikersnaam())
                .password(medewerker.getWachtwoord())
                .authorities("USER")
                .build();
    }
}
