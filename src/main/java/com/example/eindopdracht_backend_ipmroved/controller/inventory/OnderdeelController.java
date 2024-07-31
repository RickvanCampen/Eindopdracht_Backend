package com.example.eindopdracht_backend_ipmroved.controller.inventory;

import com.example.eindopdracht_backend_ipmroved.entity.Onderdeel;
import com.example.eindopdracht_backend_ipmroved.service.OnderdeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/onderdelen")
public class OnderdeelController {

    private final OnderdeelService onderdeelService;

    @Autowired
    public OnderdeelController(OnderdeelService onderdeelService) {
        this.onderdeelService = onderdeelService;
    }

    @GetMapping
    public ResponseEntity<List<Onderdeel>> getAllOnderdelen() {
        List<Onderdeel> onderdelen = onderdeelService.getAllOnderdelen();
        return new ResponseEntity<>(onderdelen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Onderdeel> getOnderdeelById(@PathVariable Long id) {
        Optional<Onderdeel> onderdeelOptional = Optional.ofNullable(onderdeelService.getOnderdeelById(id));
        return onderdeelOptional.map(onderdeel -> new ResponseEntity<>(onderdeel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Onderdeel> createOnderdeel(@RequestBody Onderdeel onderdeel) {
        try {
            Onderdeel createdOnderdeel = onderdeelService.createOnderdeel(onderdeel);
            return new ResponseEntity<>(createdOnderdeel, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Onderdeel> updateOnderdeel(@PathVariable Long id, @RequestBody Onderdeel onderdeel) {
        Onderdeel updatedOnderdeel = onderdeelService.updateOnderdeel(id, onderdeel);
        if (updatedOnderdeel != null) {
            return new ResponseEntity<>(updatedOnderdeel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnderdeel(@PathVariable Long id) {
        onderdeelService.deleteOnderdeel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/naam")
    public ResponseEntity<List<Onderdeel>> searchOnderdelenByNaam(@RequestParam String naam) {
        List<Onderdeel> onderdelen = onderdeelService.findOnderdelenByNaam(naam);
        return new ResponseEntity<>(onderdelen, HttpStatus.OK);
    }

    @GetMapping("/search/prijs")
    public ResponseEntity<List<Onderdeel>> searchOnderdelenByPrijs(@RequestParam double prijs) {
        List<Onderdeel> onderdelen = onderdeelService.findOnderdelenByPrijsLessThan(prijs);
        return new ResponseEntity<>(onderdelen, HttpStatus.OK);
    }
}
