package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
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

    public Medewerker getMedewerkerById(Long id) {
        return medewerkerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker with id " + id + " not found"));
    }

    public Medewerker createMedewerker(Medewerker medewerker) {
        return medewerkerRepository.save(medewerker);
    }

    public Medewerker updateMedewerker(Long id, Medewerker medewerker) {
        if (!medewerkerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medewerker with id " + id + " not found");
        }
        medewerker.setId(id);
        return medewerkerRepository.save(medewerker);
    }

    public void deleteMedewerker(Long id) {
        if (!medewerkerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medewerker with id " + id + " not found");
        }
        medewerkerRepository.deleteById(id);
    }

    public Medewerker getMedewerkerByGebruikersnaam(String gebruikersnaam) {
        return Optional.ofNullable(medewerkerRepository.findByGebruikersnaam(gebruikersnaam))
                .orElseThrow(() -> new ResourceNotFoundException("Medewerker with gebruikersnaam " + gebruikersnaam + " not found"));
    }

    public Medewerker promoteMedewerker(Long id) {
        Medewerker medewerker = getMedewerkerById(id);
        medewerker.setRol("ADMIN");
        return medewerkerRepository.save(medewerker);
    }

    public Medewerker assignRoleToMedewerker(Long id, String rol) {
        Medewerker medewerker = getMedewerkerById(id);
        medewerker.setRol(rol);
        return medewerkerRepository.save(medewerker);
    }
}
