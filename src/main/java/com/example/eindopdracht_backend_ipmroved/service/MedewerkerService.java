package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.repository.MedewerkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedewerkerService {

    private final MedewerkerRepository medewerkerRepository;

    @Autowired
    public MedewerkerService(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

    public List<Medewerker> getAllMedewerkers() {
        return medewerkerRepository.findAll();
    }

    public Optional<Medewerker> getMedewerkerById(Long id) {
        return medewerkerRepository.findById(id);
    }

    public Medewerker createMedewerker(Medewerker medewerker) {
        return medewerkerRepository.save(medewerker);
    }

    public Medewerker updateMedewerker(Long id, Medewerker medewerker) {
        Optional<Medewerker> existingMedewerkerOptional = medewerkerRepository.findById(id);
        if (existingMedewerkerOptional.isPresent()) {
            medewerker.setId(id);
            return medewerkerRepository.save(medewerker);
        } else {
            return null; // Return null or throw an exception to indicate that the medewerker with the given id doesn't exist
        }
    }

    public void deleteMedewerker(Long id) {
        medewerkerRepository.deleteById(id);
    }
}
