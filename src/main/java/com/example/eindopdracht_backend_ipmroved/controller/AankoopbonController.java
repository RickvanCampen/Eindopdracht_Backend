package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Aankoopbon;
import com.example.eindopdracht_backend_ipmroved.service.AankoopbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aankoopbonnen")
public class AankoopbonController {

    private final AankoopbonService aankoopbonService;

    @Autowired
    public AankoopbonController(AankoopbonService aankoopbonService) {
        this.aankoopbonService = aankoopbonService;
    }

    @GetMapping
    public ResponseEntity<List<Aankoopbon>> getAllAankoopbonnen() {
        List<Aankoopbon> aankoopbonnen = aankoopbonService.getAllAankoopbonnen();
        return new ResponseEntity<>(aankoopbonnen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aankoopbon> getAankoopbonById(@PathVariable Long id) {
        return aankoopbonService.getAankoopbonById(id)
                .map(aankoopbon -> new ResponseEntity<>(aankoopbon, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Aankoopbon> createAankoopbon(@RequestBody Aankoopbon aankoopbon) {
        Aankoopbon createdAankoopbon = aankoopbonService.createAankoopbon(aankoopbon);
        return new ResponseEntity<>(createdAankoopbon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aankoopbon> updateAankoopbon(@PathVariable Long id, @RequestBody Aankoopbon aankoopbon) {
        Aankoopbon updatedAankoopbon = aankoopbonService.updateAankoopbon(id, aankoopbon);
        if (updatedAankoopbon != null) {
            return new ResponseEntity<>(updatedAankoopbon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAankoopbon(@PathVariable Long id) {
        aankoopbonService.deleteAankoopbon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
