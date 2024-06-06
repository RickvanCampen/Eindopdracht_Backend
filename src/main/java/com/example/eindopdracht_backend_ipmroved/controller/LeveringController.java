package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Levering;
import com.example.eindopdracht_backend_ipmroved.service.LeveringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leveringen")
public class LeveringController {

    private final LeveringService leveringService;

    @Autowired
    public LeveringController(LeveringService leveringService) {
        this.leveringService = leveringService;
    }

    @GetMapping
    public ResponseEntity<List<Levering>> getAllLeveringen() {
        List<Levering> leveringen = leveringService.getAllLeveringen();
        return new ResponseEntity<>(leveringen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Levering> getLeveringById(@PathVariable Long id) {
        Optional<Levering> levering = leveringService.getLeveringById(id);
        return levering.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Levering> createLevering(@RequestBody Levering levering) {
        Levering createdLevering = leveringService.createLevering(levering);
        return new ResponseEntity<>(createdLevering, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Levering> updateLevering(@PathVariable Long id, @RequestBody Levering levering) {
        Levering updatedLevering = leveringService.updateLevering(id, levering);
        if (updatedLevering != null) {
            return new ResponseEntity<>(updatedLevering, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevering(@PathVariable Long id) {
        leveringService.deleteLevering(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
