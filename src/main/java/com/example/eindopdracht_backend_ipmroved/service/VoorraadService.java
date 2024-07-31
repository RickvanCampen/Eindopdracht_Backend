package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Voorraad;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.InvalidDataException;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.VoorraadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Voorraad getVoorraadById(Long id) {
        return voorraadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voorraad with id " + id + " not found"));
    }

    public Voorraad createVoorraad(Voorraad voorraad) {
        validateVoorraad(voorraad);
        return voorraadRepository.save(voorraad);
    }

    public Voorraad updateVoorraad(Long id, Voorraad voorraad) {
        if (!voorraadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Voorraad with id " + id + " not found");
        }
        validateVoorraad(voorraad);
        voorraad.setId(id);
        return voorraadRepository.save(voorraad);
    }

    public void deleteVoorraad(Long id) {
        if (!voorraadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Voorraad with id " + id + " not found");
        }
        voorraadRepository.deleteById(id);
    }

    private void validateVoorraad(Voorraad voorraad) {
        if (voorraad.getAantal() < 0) {
            throw new InvalidDataException("Invalid voorraad data: aantal cannot be negative");
        }
        if (voorraad.getProduct() == null) {
            throw new InvalidDataException("Invalid voorraad data: product cannot be null");
        }
    }
}
