package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.BicycleGarage;
import com.example.eindopdracht_backend_ipmroved.service.BicycleGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bicycle-garages")
public class BicycleGarageController {

    @Autowired
    private BicycleGarageService bicycleGarageService;

    @GetMapping
    public ResponseEntity<List<BicycleGarage>> getAllBicycleGarages() {
        List<BicycleGarage> bicycleGarages = bicycleGarageService.getAllBicycleGarages();
        return ResponseEntity.ok(bicycleGarages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BicycleGarage> getBicycleGarageById(@PathVariable int id) {
        Optional<BicycleGarage> bicycleGarage = bicycleGarageService.getBicycleGarageById(id);
        return bicycleGarage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BicycleGarage> createBicycleGarage(@RequestBody BicycleGarage bicycleGarage) {
        BicycleGarage savedBicycleGarage = bicycleGarageService.saveBicycleGarage(bicycleGarage);
        return ResponseEntity.ok(savedBicycleGarage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BicycleGarage> updateBicycleGarage(@PathVariable int id, @RequestBody BicycleGarage bicycleGarage) {
        if (bicycleGarageService.getBicycleGarageById(id).isPresent()) {
            bicycleGarage.setId(id);
            BicycleGarage updatedBicycleGarage = bicycleGarageService.saveBicycleGarage(bicycleGarage);
            return ResponseEntity.ok(updatedBicycleGarage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBicycleGarage(@PathVariable int id) {
        if (bicycleGarageService.getBicycleGarageById(id).isPresent()) {
            bicycleGarageService.deleteBicycleGarage(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
