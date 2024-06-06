package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.BestellingOnderdeel;
import com.example.eindopdracht_backend_ipmroved.repository.BestellingOnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<BestellingOnderdeel> getBestellingOnderdeelById(Long id) {
        return bestellingOnderdeelRepository.findById(id);
    }

    public BestellingOnderdeel createBestellingOnderdeel(BestellingOnderdeel bestellingOnderdeel) {
        return bestellingOnderdeelRepository.save(bestellingOnderdeel);
    }

    public BestellingOnderdeel updateBestellingOnderdeel(Long id, BestellingOnderdeel bestellingOnderdeel) {
        if (bestellingOnderdeelRepository.existsById(id)) {
            bestellingOnderdeel.setId(id);
            return bestellingOnderdeelRepository.save(bestellingOnderdeel);
        } else {
            return null;
        }
    }

    public void deleteBestellingOnderdeel(Long id) {
        bestellingOnderdeelRepository.deleteById(id);
    }
}
