package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Factuur;
import com.example.eindopdracht_backend_ipmroved.service.FactuurService;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturen")
public class FactuurController {

    private final FactuurService factuurService;

    @Autowired
    public FactuurController(FactuurService factuurService) {
        this.factuurService = factuurService;
    }

    @GetMapping
    public ResponseEntity<List<Factuur>> getAllFacturen() {
        List<Factuur> facturen = factuurService.getAllFacturen();
        return new ResponseEntity<>(facturen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factuur> getFactuurById(@PathVariable Long id) {
        try {
            Factuur factuur = factuurService.getFactuurById(id);
            return new ResponseEntity<>(factuur, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Factuur> createFactuur(@RequestBody Factuur factuur) {
        Factuur createdFactuur = factuurService.createFactuur(factuur);
        return new ResponseEntity<>(createdFactuur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factuur> updateFactuur(@PathVariable Long id, @RequestBody Factuur factuur) {
        try {
            Factuur updatedFactuur = factuurService.updateFactuur(id, factuur);
            return new ResponseEntity<>(updatedFactuur, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactuur(@PathVariable Long id) {
        try {
            factuurService.deleteFactuur(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
