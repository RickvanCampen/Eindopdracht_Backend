package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Reparatie;
import com.example.eindopdracht_backend_ipmroved.service.ReparatieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reparaties")
public class ReparatieController {

    private final ReparatieService reparatieService;

    @Autowired
    public ReparatieController(ReparatieService reparatieService) {
        this.reparatieService = reparatieService;
    }

    @GetMapping
    public ResponseEntity<List<Reparatie>> getAllReparaties() {
        List<Reparatie> reparaties = reparatieService.getAllReparaties();
        return new ResponseEntity<>(reparaties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reparatie> getReparatieById(@PathVariable Long id) {
        Optional<Reparatie> reparatieOptional = reparatieService.getReparatieById(id);
        return reparatieOptional.map(reparatie -> new ResponseEntity<>(reparatie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Reparatie> createReparatie(@RequestBody Reparatie reparatie) {
        Reparatie createdReparatie = reparatieService.createReparatie(reparatie);
        return new ResponseEntity<>(createdReparatie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reparatie> updateReparatie(@PathVariable Long id, @RequestBody Reparatie reparatie) {
        Reparatie updatedReparatie = reparatieService.updateReparatie(id, reparatie);
        if (updatedReparatie != null) {
            return new ResponseEntity<>(updatedReparatie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparatie(@PathVariable Long id) {
        reparatieService.deleteReparatie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
