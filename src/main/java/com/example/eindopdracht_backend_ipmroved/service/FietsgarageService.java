package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Fietsgarage;
import com.example.eindopdracht_backend_ipmroved.repository.FietsgarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Fietsgarage> getFietsgarageById(Long id) {
        return fietsgarageRepository.findById(id);
    }

    public Fietsgarage createFietsgarage(Fietsgarage fietsgarage) {
        return fietsgarageRepository.save(fietsgarage);
    }

    public Fietsgarage updateFietsgarage(Long id, Fietsgarage fietsgarage) {
        if (fietsgarageRepository.existsById(id)) {
            fietsgarage.setId(id);
            return fietsgarageRepository.save(fietsgarage);
        } else {
            return null;
        }
    }

    public void deleteFietsgarage(Long id) {
        fietsgarageRepository.deleteById(id);
    }
}
