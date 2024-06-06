package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Afspraak;
import com.example.eindopdracht_backend_ipmroved.service.AfspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/afspraken")
public class AfspraakController {

    private final AfspraakService afspraakService;

    @Autowired
    public AfspraakController(AfspraakService afspraakService) {
        this.afspraakService = afspraakService;
    }

    @GetMapping
    public ResponseEntity<List<Afspraak>> getAllAfspraken() {
        List<Afspraak> afspraken = afspraakService.getAllAfspraken();
        return new ResponseEntity<>(afspraken, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afspraak> getAfspraakById(@PathVariable Long id) {
        return afspraakService.getAfspraakById(id)
                .map(afspraak -> new ResponseEntity<>(afspraak, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Afspraak> createAfspraak(@RequestBody Afspraak afspraak) {
        Afspraak createdAfspraak = afspraakService.createAfspraak(afspraak);
        return new ResponseEntity<>(createdAfspraak, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Afspraak> updateAfspraak(@PathVariable Long id, @RequestBody Afspraak afspraak) {
        Afspraak updatedAfspraak = afspraakService.updateAfspraak(id, afspraak);
        if (updatedAfspraak != null) {
            return new ResponseEntity<>(updatedAfspraak, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAfspraak(@PathVariable Long id) {
        afspraakService.deleteAfspraak(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
