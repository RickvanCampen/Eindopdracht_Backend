package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Gebruiker;
import com.example.eindopdracht_backend_ipmroved.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gebruikers")
public class GebruikerController {

    private final GebruikerService gebruikerService;

    @Autowired
    public GebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @PostMapping("/registreren")
    public ResponseEntity<Gebruiker> registreren(@RequestBody Gebruiker gebruiker) {
        Gebruiker bestaandeGebruiker = gebruikerService.findByGebruikersnaam(gebruiker.getGebruikersnaam());
        if (bestaandeGebruiker != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Gebruiker bestaat al
        }
        Gebruiker nieuweGebruiker = gebruikerService.saveGebruiker(gebruiker);
        return new ResponseEntity<>(nieuweGebruiker, HttpStatus.CREATED);
    }
}
