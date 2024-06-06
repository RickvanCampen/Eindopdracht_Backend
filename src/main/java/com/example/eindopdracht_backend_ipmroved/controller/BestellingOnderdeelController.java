package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.BestellingOnderdeel;
import com.example.eindopdracht_backend_ipmroved.service.BestellingOnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bestelling-onderdelen")
public class BestellingOnderdeelController {

    private final BestellingOnderdeelService bestellingOnderdeelService;

    @Autowired
    public BestellingOnderdeelController(BestellingOnderdeelService bestellingOnderdeelService) {
        this.bestellingOnderdeelService = bestellingOnderdeelService;
    }

    @GetMapping
    public ResponseEntity<List<BestellingOnderdeel>> getAllBestellingOnderdelen() {
        List<BestellingOnderdeel> bestellingOnderdelen = bestellingOnderdeelService.getAllBestellingOnderdelen();
        return new ResponseEntity<>(bestellingOnderdelen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BestellingOnderdeel> getBestellingOnderdeelById(@PathVariable Long id) {
        Optional<BestellingOnderdeel> bestellingOnderdeel = bestellingOnderdeelService.getBestellingOnderdeelById(id);
        return bestellingOnderdeel.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BestellingOnderdeel> createBestellingOnderdeel(@RequestBody BestellingOnderdeel bestellingOnderdeel) {
        BestellingOnderdeel createdBestellingOnderdeel = bestellingOnderdeelService.createBestellingOnderdeel(bestellingOnderdeel);
        return new ResponseEntity<>(createdBestellingOnderdeel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BestellingOnderdeel> updateBestellingOnderdeel(@PathVariable Long id, @RequestBody BestellingOnderdeel bestellingOnderdeel) {
        BestellingOnderdeel updatedBestellingOnderdeel = bestellingOnderdeelService.updateBestellingOnderdeel(id, bestellingOnderdeel);
        if (updatedBestellingOnderdeel != null) {
            return new ResponseEntity<>(updatedBestellingOnderdeel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBestellingOnderdeel(@PathVariable Long id) {
        bestellingOnderdeelService.deleteBestellingOnderdeel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
