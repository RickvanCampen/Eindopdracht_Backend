package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/klanten")
public class KlantController {

    private final KlantService klantService;

    @Autowired
    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping
    public ResponseEntity<List<Klant>> getAllKlanten() {
        List<Klant> klanten = klantService.getAllKlanten();
        return new ResponseEntity<>(klanten, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klant> getKlantById(@PathVariable("id") Long id) {
        Optional<Klant> klant = klantService.getKlantById(id);
        return klant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Klant> createKlant(@RequestBody Klant klant) {
        Klant createdKlant = klantService.createKlant(klant);
        return new ResponseEntity<>(createdKlant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Klant> updateKlant(@PathVariable("id") Long id, @RequestBody Klant klant) {
        Klant updatedKlant = klantService.updateKlant(id, klant);
        if (updatedKlant != null) {
            return new ResponseEntity<>(updatedKlant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKlant(@PathVariable("id") Long id) {
        klantService.deleteKlant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
