package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.service.MedewerkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medewerkers")
public class MedewerkerController {

    private final MedewerkerService medewerkerService;

    @Autowired
    public MedewerkerController(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    @GetMapping
    public ResponseEntity<List<Medewerker>> getAllMedewerkers() {
        List<Medewerker> medewerkers = medewerkerService.getAllMedewerkers();
        return new ResponseEntity<>(medewerkers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medewerker> getMedewerkerById(@PathVariable Long id) {
        Optional<Medewerker> medewerker = medewerkerService.getMedewerkerById(id);
        return medewerker.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Medewerker> createMedewerker(@RequestBody Medewerker medewerker) {
        Medewerker createdMedewerker = medewerkerService.createMedewerker(medewerker);
        return new ResponseEntity<>(createdMedewerker, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medewerker> updateMedewerker(@PathVariable Long id, @RequestBody Medewerker medewerker) {
        Medewerker updatedMedewerker = medewerkerService.updateMedewerker(id, medewerker);
        if (updatedMedewerker != null) {
            return new ResponseEntity<>(updatedMedewerker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedewerker(@PathVariable Long id) {
        medewerkerService.deleteMedewerker(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
