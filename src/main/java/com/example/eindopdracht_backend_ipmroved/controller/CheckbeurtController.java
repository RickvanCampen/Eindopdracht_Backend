package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.entity.Checkbeurt;
import com.example.eindopdracht_backend_ipmroved.service.CheckbeurtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkbeurten")
public class CheckbeurtController {

    private final CheckbeurtService checkbeurtService;

    @Autowired
    public CheckbeurtController(CheckbeurtService checkbeurtService) {
        this.checkbeurtService = checkbeurtService;
    }

    @GetMapping
    public ResponseEntity<List<Checkbeurt>> getAllCheckbeurten() {
        List<Checkbeurt> checkbeurten = checkbeurtService.getAllCheckbeurten();
        return new ResponseEntity<>(checkbeurten, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checkbeurt> getCheckbeurtById(@PathVariable Long id) {
        return checkbeurtService.getCheckbeurtById(id)
                .map(checkbeurt -> new ResponseEntity<>(checkbeurt, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Checkbeurt> createCheckbeurt(@RequestBody Checkbeurt checkbeurt) {
        Checkbeurt createdCheckbeurt = checkbeurtService.createCheckbeurt(checkbeurt);
        return new ResponseEntity<>(createdCheckbeurt, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checkbeurt> updateCheckbeurt(@PathVariable Long id, @RequestBody Checkbeurt checkbeurt) {
        Checkbeurt updatedCheckbeurt = checkbeurtService.updateCheckbeurt(id, checkbeurt);
        if (updatedCheckbeurt != null) {
            return new ResponseEntity<>(updatedCheckbeurt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckbeurt(@PathVariable Long id) {
        checkbeurtService.deleteCheckbeurt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
