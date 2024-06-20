package com.example.eindopdracht_backend_ipmroved.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String storeFile(String entity, Long id, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new StorageException("Failed to store file with null filename");
            }

            Path entityPath = rootLocation.resolve(Paths.get(entity, id.toString()));
            Files.createDirectories(entityPath);
            Path destinationFile = entityPath.resolve(originalFilename).normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile);

            return destinationFile.toString();
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Path loadFile(String entity, Long id, String filename) {
        return rootLocation.resolve(Paths.get(entity, id.toString(), filename)).normalize().toAbsolutePath();
    }

    @Override
    public void deleteFile(String entity, Long id, String filename) {
        try {
            Path filePath = loadFile(entity, id, filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Failed to delete file", e);
        }
    }
}
