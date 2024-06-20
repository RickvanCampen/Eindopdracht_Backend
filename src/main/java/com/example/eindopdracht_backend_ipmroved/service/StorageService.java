package com.example.eindopdracht_backend_ipmroved.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    String storeFile(String entity, Long id, MultipartFile file);

    Path loadFile(String entity, Long id, String filename);

    void deleteFile(String entity, Long id, String filename);
}

