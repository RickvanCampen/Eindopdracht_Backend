package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.BestellingOnderdeel;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.BestellingOnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellingOnderdeelService {

    private final BestellingOnderdeelRepository bestellingOnderdeelRepository;

    @Autowired
    public BestellingOnderdeelService(BestellingOnderdeelRepository bestellingOnderdeelRepository) {
        this.bestellingOnderdeelRepository = bestellingOnderdeelRepository;
    }

    public List<BestellingOnderdeel> getAllBestellingOnderdelen() {
        return bestellingOnderdeelRepository.findAll();
    }

    public BestellingOnderdeel getBestellingOnderdeelById(Long id) {
        return bestellingOnderdeelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BestellingOnderdeel met id " + id + " niet gevonden"));
    }

    public BestellingOnderdeel createBestellingOnderdeel(BestellingOnderdeel bestellingOnderdeel) {
        return bestellingOnderdeelRepository.save(bestellingOnderdeel);
    }

    public BestellingOnderdeel updateBestellingOnderdeel(Long id, BestellingOnderdeel bestellingOnderdeel) {
        if (!bestellingOnderdeelRepository.existsById(id)) {
            throw new ResourceNotFoundException("BestellingOnderdeel met id " + id + " niet gevonden");
        }
        bestellingOnderdeel.setId(id);
        return bestellingOnderdeelRepository.save(bestellingOnderdeel);
    }

    public void deleteBestellingOnderdeel(Long id) {
        if (!bestellingOnderdeelRepository.existsById(id)) {
            throw new ResourceNotFoundException("BestellingOnderdeel met id " + id + " niet gevonden");
        }
        bestellingOnderdeelRepository.deleteById(id);
    }
}
