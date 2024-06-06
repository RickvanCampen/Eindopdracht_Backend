package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Factuur;
import com.example.eindopdracht_backend_ipmroved.repository.FactuurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Factuur> getFactuurById(Long id) {
        return factuurRepository.findById(id);
    }

    public Factuur createFactuur(Factuur factuur) {
        return factuurRepository.save(factuur);
    }

    public Factuur updateFactuur(Long id, Factuur factuur) {
        if (factuurRepository.existsById(id)) {
            factuur.setId(id);
            return factuurRepository.save(factuur);
        } else {
            return null; // Return null or throw an exception to indicate that the factuur with the given id doesn't exist
        }
    }

    public void deleteFactuur(Long id) {
        factuurRepository.deleteById(id);
    }
}
