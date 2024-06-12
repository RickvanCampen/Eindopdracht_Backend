package com.example.eindopdracht_backend_ipmroved.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final String uploadDir = "uploads";  // Directory where files will be stored

    public FileController() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            // Directory already exists or could not be created
        }
    }

    @PostMapping("/{entity}/{id}")
    public ResponseEntity<String> uploadFile(
            @PathVariable String entity,
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            }

            Path entityPath = Paths.get(uploadDir, entity, id.toString());
            Files.createDirectories(entityPath);

            Path filePath = entityPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + filePath.toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload file: " + e.getMessage());
        }
    }

    @GetMapping("/{entity}/{id}/{filename}")
    public ResponseEntity<byte[]> getFile(
            @PathVariable String entity,
            @PathVariable Long id,
            @PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir, entity, id.toString(), filename);
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
}
