package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Reparatie;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.ReparatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Reparatie getReparatieById(Long id) {
        return reparatieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reparatie with id " + id + " not found"));
    }

    public Reparatie createReparatie(Reparatie reparatie) {
        return reparatieRepository.save(reparatie);
    }

    public Reparatie updateReparatie(Long id, Reparatie reparatie) {
        if (!reparatieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reparatie with id " + id + " not found");
        }
        reparatie.setId(id);
        return reparatieRepository.save(reparatie);
    }

    public void deleteReparatie(Long id) {
        if (!reparatieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reparatie with id " + id + " not found");
        }
        reparatieRepository.deleteById(id);
    }
}
