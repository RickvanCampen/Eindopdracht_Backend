package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Fietsgarage;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.FietsgarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FietsgarageService {

    private final FietsgarageRepository fietsgarageRepository;

    @Autowired
    public FietsgarageService(FietsgarageRepository fietsgarageRepository) {
        this.fietsgarageRepository = fietsgarageRepository;
    }

    public List<Fietsgarage> getAllFietsgarages() {
        return fietsgarageRepository.findAll();
    }

    public Fietsgarage getFietsgarageById(Long id) {
        return fietsgarageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fietsgarage met id " + id + " niet gevonden"));
    }

    public Fietsgarage createFietsgarage(Fietsgarage fietsgarage) {
        return fietsgarageRepository.save(fietsgarage);
    }

    public Fietsgarage updateFietsgarage(Long id, Fietsgarage fietsgarage) {
        if (!fietsgarageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fietsgarage met id " + id + " niet gevonden");
        }
        fietsgarage.setId(id);
        return fietsgarageRepository.save(fietsgarage);
    }

    public void deleteFietsgarage(Long id) {
        if (!fietsgarageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fietsgarage met id " + id + " niet gevonden");
        }
        fietsgarageRepository.deleteById(id);
    }
}
