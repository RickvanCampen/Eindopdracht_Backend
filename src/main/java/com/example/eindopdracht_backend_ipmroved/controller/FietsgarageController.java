package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Fietsgarage;
import com.example.eindopdracht_backend_ipmroved.service.FietsgarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fietsgarages")
public class FietsgarageController {

    private final FietsgarageService fietsgarageService;

    @Autowired
    public FietsgarageController(FietsgarageService fietsgarageService) {
        this.fietsgarageService = fietsgarageService;
    }

    @GetMapping
    public ResponseEntity<List<Fietsgarage>> getAllFietsgarages() {
        List<Fietsgarage> fietsgarages = fietsgarageService.getAllFietsgarages();
        return new ResponseEntity<>(fietsgarages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fietsgarage> getFietsgarageById(@PathVariable Long id) {
        return fietsgarageService.getFietsgarageById(id)
                .map(fietsgarage -> new ResponseEntity<>(fietsgarage, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Fietsgarage> createFietsgarage(@RequestBody Fietsgarage fietsgarage) {
        Fietsgarage createdFietsgarage = fietsgarageService.createFietsgarage(fietsgarage);
        return new ResponseEntity<>(createdFietsgarage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fietsgarage> updateFietsgarage(@PathVariable Long id, @RequestBody Fietsgarage fietsgarage) {
        Fietsgarage updatedFietsgarage = fietsgarageService.updateFietsgarage(id, fietsgarage);
        if (updatedFietsgarage != null) {
            return new ResponseEntity<>(updatedFietsgarage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFietsgarage(@PathVariable Long id) {
        fietsgarageService.deleteFietsgarage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
