package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Levering;
import com.example.eindopdracht_backend_ipmroved.repository.LeveringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeveringService {

    private final LeveringRepository leveringRepository;

    @Autowired
    public LeveringService(LeveringRepository leveringRepository) {
        this.leveringRepository = leveringRepository;
    }

    public List<Levering> getAllLeveringen() {
        return leveringRepository.findAll();
    }

    public Optional<Levering> getLeveringById(Long id) {
        return leveringRepository.findById(id);
    }

    public Levering createLevering(Levering levering) {
        return leveringRepository.save(levering);
    }

    public Levering updateLevering(Long id, Levering levering) {
        if (leveringRepository.existsById(id)) {
            levering.setId(id);
            return leveringRepository.save(levering);
        } else {
            return null; // Return null or throw an exception to indicate that the levering with the given id doesn't exist
        }
    }

    public void deleteLevering(Long id) {
        leveringRepository.deleteById(id);
    }
}
