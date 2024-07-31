package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.dto.KlantDTO;
import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/klanten")
public class KlantController {

    private final KlantService klantService;

    @Autowired
    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping
    public ResponseEntity<List<KlantDTO>> getAllKlanten() {
        try {
            List<Klant> klanten = klantService.getAllKlanten();
            List<KlantDTO> klantDTOs = klanten.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(klantDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<KlantDTO> getKlantById(@PathVariable("id") Long id) {
        try {
            Klant klant = klantService.getKlantById(id).orElseThrow(() -> new RuntimeException("Klant not found"));
            return new ResponseEntity<>(convertToDTO(klant), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<KlantDTO> createKlant(@Valid @RequestBody KlantDTO klantDTO) {
        try {
            Klant klant = convertToEntity(klantDTO);
            Klant createdKlant = klantService.createKlant(klant);
            return new ResponseEntity<>(convertToDTO(createdKlant), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<KlantDTO> updateKlant(@PathVariable("id") Long id, @Valid @RequestBody KlantDTO klantDTO) {
        try {
            Klant klant = convertToEntity(klantDTO);
            Klant updatedKlant = klantService.updateKlant(id, klant);
            if (updatedKlant != null) {
                return new ResponseEntity<>(convertToDTO(updatedKlant), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKlant(@PathVariable("id") Long id) {
        try {
            klantService.deleteKlant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/upgrade")
    public ResponseEntity<KlantDTO> upgradeKlantNaarPremium(@PathVariable("id") Long id) {
        try {
            Optional<Klant> upgradedKlantOpt = klantService.upgradeKlantNaarPremium(id);
            return upgradedKlantOpt
                    .map(upgradedKlant -> new ResponseEntity<>(convertToDTO(upgradedKlant), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/premium")
    public ResponseEntity<List<KlantDTO>> getAllPremiumKlanten() {
        try {
            List<Klant> premiumKlanten = klantService.getAllPremiumKlanten();
            List<KlantDTO> klantDTOs = premiumKlanten.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(klantDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> error.getDefaultMessage()
                ));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private KlantDTO convertToDTO(Klant klant) {
        return new KlantDTO(
                klant.getId(),
                klant.getVoornaam(),
                klant.getAchternaam(),
                klant.getEmail(),
                klant.getTelefoonnummer(),
                klant.getAdres(),
                klant.getWoonplaats(),
                klant.getPostcode(),
                klant.isPremium(),
                klant.getAankoopGeschiedenis()
        );
    }

    private Klant convertToEntity(KlantDTO klantDTO) {
        return new Klant(
                klantDTO.getId(),
                klantDTO.getVoornaam(),
                klantDTO.getAchternaam(),
                klantDTO.getEmail(),
                klantDTO.getTelefoonnummer(),
                klantDTO.getAdres(),
                klantDTO.getWoonplaats(),
                klantDTO.getPostcode(),
                klantDTO.isPremium(),
                klantDTO.getAankoopGeschiedenis()
        );
    }
}
