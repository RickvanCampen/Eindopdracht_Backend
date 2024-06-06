package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Betaalmethode;
import com.example.eindopdracht_backend_ipmroved.service.BetaalmethodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/betaalmethoden")
public class BetaalmethodeController {

    private final BetaalmethodeService betaalmethodeService;

    @Autowired
    public BetaalmethodeController(BetaalmethodeService betaalmethodeService) {
        this.betaalmethodeService = betaalmethodeService;
    }

    @GetMapping
    public ResponseEntity<List<Betaalmethode>> getAllBetaalmethoden() {
        List<Betaalmethode> betaalmethoden = betaalmethodeService.getAllBetaalmethoden();
        return new ResponseEntity<>(betaalmethoden, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Betaalmethode> getBetaalmethodeById(@PathVariable Long id) {
        return betaalmethodeService.getBetaalmethodeById(id)
                .map(betaalmethode -> new ResponseEntity<>(betaalmethode, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Betaalmethode> createBetaalmethode(@RequestBody Betaalmethode betaalmethode) {
        Betaalmethode createdBetaalmethode = betaalmethodeService.createBetaalmethode(betaalmethode);
        return new ResponseEntity<>(createdBetaalmethode, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Betaalmethode> updateBetaalmethode(@PathVariable Long id, @RequestBody Betaalmethode betaalmethode) {
        Betaalmethode updatedBetaalmethode = betaalmethodeService.updateBetaalmethode(id, betaalmethode);
        if (updatedBetaalmethode != null) {
            return new ResponseEntity<>(updatedBetaalmethode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBetaalmethode(@PathVariable Long id) {
        betaalmethodeService.deleteBetaalmethode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
