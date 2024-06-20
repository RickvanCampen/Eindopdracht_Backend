package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
        this.storageService.init();
    }

    @PostMapping("/{entity}/{id}")
    public ResponseEntity<String> uploadFile(
            @PathVariable String entity,
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String filePath = storageService.storeFile(entity, id, file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload file: " + e.getMessage());
        }
    }

    @GetMapping("/{entity}/{id}/{filename}")
    public ResponseEntity<byte[]> getFile(
            @PathVariable String entity,
            @PathVariable Long id,
            @PathVariable String filename) {
        try {
            Path filePath = storageService.loadFile(entity, id, filename);
            if (Files.exists(filePath)) {
                byte[] fileData = Files.readAllBytes(filePath);
                return ResponseEntity.status(HttpStatus.OK).body(fileData);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{entity}/{id}/{filename}")
    public ResponseEntity<String> deleteFile(
            @PathVariable String entity,
            @PathVariable Long id,
            @PathVariable String filename) {
        try {
            storageService.deleteFile(entity, id, filename);
            return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete file: " + e.getMessage());
        }
    }
}
