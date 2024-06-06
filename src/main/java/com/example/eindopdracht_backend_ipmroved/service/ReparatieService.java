package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Reparatie;
import com.example.eindopdracht_backend_ipmroved.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReparatieService {

    private final ReparatieRepository reparatieRepository;

    @Autowired
    public ReparatieService(ReparatieRepository reparatieRepository) {
        this.reparatieRepository = reparatieRepository;
    }

    public List<Reparatie> getAllReparaties() {
        return reparatieRepository.findAll();
    }

    public Optional<Reparatie> getReparatieById(Long id) {
        return reparatieRepository.findById(id);
    }

    public Reparatie createReparatie(Reparatie reparatie) {
        return reparatieRepository.save(reparatie);
    }

    public Reparatie updateReparatie(Long id, Reparatie reparatie) {
        Optional<Reparatie> existingReparatieOptional = reparatieRepository.findById(id);
        if (existingReparatieOptional.isPresent()) {
            reparatie.setId(id);
            return reparatieRepository.save(reparatie);
        } else {
            return null; // Return null or throw an exception to indicate that the reparatie with the given id doesn't exist
        }
    }

    public void deleteReparatie(Long id) {
        reparatieRepository.deleteById(id);
    }
}
