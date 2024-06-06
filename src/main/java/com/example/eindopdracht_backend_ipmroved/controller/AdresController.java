package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Adres;
import com.example.eindopdracht_backend_ipmroved.service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adressen")
public class AdresController {

    private final AdresService adresService;

    @Autowired
    public AdresController(AdresService adresService) {
        this.adresService = adresService;
    }

    @GetMapping
    public ResponseEntity<List<Adres>> getAllAdressen() {
        List<Adres> adressen = adresService.getAllAdressen();
        return new ResponseEntity<>(adressen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adres> getAdresById(@PathVariable Long id) {
        return adresService.getAdresById(id)
                .map(adres -> new ResponseEntity<>(adres, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Adres> createAdres(@RequestBody Adres adres) {
        Adres createdAdres = adresService.createAdres(adres);
        return new ResponseEntity<>(createdAdres, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adres> updateAdres(@PathVariable Long id, @RequestBody Adres adres) {
        Adres updatedAdres = adresService.updateAdres(id, adres);
        if (updatedAdres != null) {
            return new ResponseEntity<>(updatedAdres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdres(@PathVariable Long id) {
        adresService.deleteAdres(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
