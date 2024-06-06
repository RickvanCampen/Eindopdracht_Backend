package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Onderdeel;
import com.example.eindopdracht_backend_ipmroved.repository.OnderdeelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Onderdeel> getOnderdeelById(Long id) {
        return onderdeelRepository.findById(id);
    }

    public Onderdeel createOnderdeel(Onderdeel onderdeel) {
        return onderdeelRepository.save(onderdeel);
    }

    public Onderdeel updateOnderdeel(Long id, Onderdeel onderdeel) {
        Optional<Onderdeel> existingOnderdeelOptional = onderdeelRepository.findById(id);
        if (existingOnderdeelOptional.isPresent()) {
            onderdeel.setId(id);
            return onderdeelRepository.save(onderdeel);
        } else {
            return null; // Return null or throw an exception to indicate that the onderdeel with the given id doesn't exist
        }
    }

    public void deleteOnderdeel(Long id) {
        onderdeelRepository.deleteById(id);
    }
}
