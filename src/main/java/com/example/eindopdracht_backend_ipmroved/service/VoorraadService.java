package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Voorraad;
import com.example.eindopdracht_backend_ipmroved.repository.VoorraadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoorraadService {

    private final VoorraadRepository voorraadRepository;

    @Autowired
    public VoorraadService(VoorraadRepository voorraadRepository) {
        this.voorraadRepository = voorraadRepository;
    }

    public List<Voorraad> getAllVoorraden() {
        return voorraadRepository.findAll();
    }

    public Optional<Voorraad> getVoorraadById(Long id) {
        return voorraadRepository.findById(id);
    }

    public Voorraad createVoorraad(Voorraad voorraad) {
        return voorraadRepository.save(voorraad);
    }

    public Voorraad updateVoorraad(Long id, Voorraad voorraad) {
        Optional<Voorraad> existingVoorraadOptional = voorraadRepository.findById(id);
        if (existingVoorraadOptional.isPresent()) {
            voorraad.setId(id);
            return voorraadRepository.save(voorraad);
        } else {
            return null; // Return null or throw an exception to indicate that the voorraad with the given id doesn't exist
        }
    }

    public void deleteVoorraad(Long id) {
        voorraadRepository.deleteById(id);
    }
}
