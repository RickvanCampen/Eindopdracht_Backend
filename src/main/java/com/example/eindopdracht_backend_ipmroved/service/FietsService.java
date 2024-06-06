package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Fiets;
import com.example.eindopdracht_backend_ipmroved.repository.FietsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FietsService {

    private final FietsRepository fietsRepository;

    @Autowired
    public FietsService(FietsRepository fietsRepository) {
        this.fietsRepository = fietsRepository;
    }

    public List<Fiets> getAllFietsen() {
        return fietsRepository.findAll();
    }

    public Optional<Fiets> getFietsById(Long id) {
        return fietsRepository.findById(id);
    }

    public Fiets createFiets(Fiets fiets) {
        return fietsRepository.save(fiets);
    }

    public Fiets updateFiets(Long id, Fiets fiets) {
        if (fietsRepository.existsById(id)) {
            fiets.setId(id);
            return fietsRepository.save(fiets);
        } else {
            return null; // Return null or throw an exception to indicate that the fiets with the given id doesn't exist
        }
    }

    public void deleteFiets(Long id) {
        fietsRepository.deleteById(id);
    }
}
