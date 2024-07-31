package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Factuur;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.FactuurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactuurService {

    private final FactuurRepository factuurRepository;

    @Autowired
    public FactuurService(FactuurRepository factuurRepository) {
        this.factuurRepository = factuurRepository;
    }

    public List<Factuur> getAllFacturen() {
        return factuurRepository.findAll();
    }

    public Factuur getFactuurById(Long id) {
        return factuurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Factuur", "id", id));
    }

    public Factuur createFactuur(Factuur factuur) {
        return factuurRepository.save(factuur);
    }

    public Factuur updateFactuur(Long id, Factuur factuur) {
        if (!factuurRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Factuur", "id", id);
        }
        factuur.setId(id);
        return factuurRepository.save(factuur);
    }

    public void deleteFactuur(Long id) {
        if (!factuurRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Factuur", "id", id);
        }
        factuurRepository.deleteById(id);
    }
}
