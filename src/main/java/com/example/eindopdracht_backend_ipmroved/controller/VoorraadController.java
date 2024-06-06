package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Voorraad;
import com.example.eindopdracht_backend_ipmroved.service.VoorraadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voorraad")
public class VoorraadController {

    private final VoorraadService voorraadService;

    @Autowired
    public VoorraadController(VoorraadService voorraadService) {
        this.voorraadService = voorraadService;
    }

    @GetMapping
    public ResponseEntity<List<Voorraad>> getAllVoorraden() {
        List<Voorraad> voorraden = voorraadService.getAllVoorraden();
        return new ResponseEntity<>(voorraden, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voorraad> getVoorraadById(@PathVariable Long id) {
        Optional<Voorraad> voorraadOptional = voorraadService.getVoorraadById(id);
        return voorraadOptional.map(voorraad -> new ResponseEntity<>(voorraad, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Voorraad> createVoorraad(@RequestBody Voorraad voorraad) {
        Voorraad createdVoorraad = voorraadService.createVoorraad(voorraad);
        return new ResponseEntity<>(createdVoorraad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voorraad> updateVoorraad(@PathVariable Long id, @RequestBody Voorraad voorraad) {
        Voorraad updatedVoorraad = voorraadService.updateVoorraad(id, voorraad);
        if (updatedVoorraad != null) {
            return new ResponseEntity<>(updatedVoorraad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoorraad(@PathVariable Long id) {
        voorraadService.deleteVoorraad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
