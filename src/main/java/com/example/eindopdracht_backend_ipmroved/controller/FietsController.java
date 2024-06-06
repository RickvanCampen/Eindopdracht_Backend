package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Fiets;
import com.example.eindopdracht_backend_ipmroved.service.FietsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fietsen")
public class FietsController {

    private final FietsService fietsService;

    @Autowired
    public FietsController(FietsService fietsService) {
        this.fietsService = fietsService;
    }

    @GetMapping
    public ResponseEntity<List<Fiets>> getAllFietsen() {
        List<Fiets> fietsen = fietsService.getAllFietsen();
        return new ResponseEntity<>(fietsen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fiets> getFietsById(@PathVariable Long id) {
        Optional<Fiets> fiets = fietsService.getFietsById(id);
        return fiets.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Fiets> createFiets(@RequestBody Fiets fiets) {
        Fiets createdFiets = fietsService.createFiets(fiets);
        return new ResponseEntity<>(createdFiets, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fiets> updateFiets(@PathVariable Long id, @RequestBody Fiets fiets) {
        Fiets updatedFiets = fietsService.updateFiets(id, fiets);
        return updatedFiets != null ?
                new ResponseEntity<>(updatedFiets, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiets(@PathVariable Long id) {
        fietsService.deleteFiets(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
