package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Onderdeel;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.InvalidDataException;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.OnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnderdeelService {

    private final OnderdeelRepository onderdeelRepository;

    @Autowired
    public OnderdeelService(OnderdeelRepository onderdeelRepository) {
        this.onderdeelRepository = onderdeelRepository;
    }

    public List<Onderdeel> getAllOnderdelen() {
        return onderdeelRepository.findAll();
    }

    public Onderdeel getOnderdeelById(Long id) {
        return onderdeelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Onderdeel with id " + id + " not found"));
    }

    public Onderdeel createOnderdeel(Onderdeel onderdeel) {
        validateOnderdeel(onderdeel);
        return onderdeelRepository.save(onderdeel);
    }

    public Onderdeel updateOnderdeel(Long id, Onderdeel onderdeel) {
        if (!onderdeelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Onderdeel with id " + id + " not found");
        }
        validateOnderdeel(onderdeel);
        onderdeel.setId(id);
        return onderdeelRepository.save(onderdeel);
    }

    public void deleteOnderdeel(Long id) {
        if (!onderdeelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Onderdeel with id " + id + " not found");
        }
        onderdeelRepository.deleteById(id);
    }

    public List<Onderdeel> findOnderdelenByNaam(String naam) {
        return onderdeelRepository.findByNaamContainingIgnoreCase(naam);
    }

    public List<Onderdeel> findOnderdelenByPrijsLessThan(double prijs) {
        return onderdeelRepository.findByPrijsLessThan(prijs);
    }

    private void validateOnderdeel(Onderdeel onderdeel) {
        if (onderdeel.getNaam() == null || onderdeel.getPrijs() <= 0) {
            throw new InvalidDataException("Invalid onderdeel data: naam and prijs must be valid");
        }
    }
}
