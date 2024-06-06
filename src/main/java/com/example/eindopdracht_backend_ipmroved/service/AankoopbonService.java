package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Aankoopbon;
import com.example.eindopdracht_backend_ipmroved.repository.AankoopbonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AankoopbonService {

    private final AankoopbonRepository aankoopbonRepository;

    @Autowired
    public AankoopbonService(AankoopbonRepository aankoopbonRepository) {
        this.aankoopbonRepository = aankoopbonRepository;
    }

    public List<Aankoopbon> getAllAankoopbonnen() {
        return aankoopbonRepository.findAll();
    }

    public Optional<Aankoopbon> getAankoopbonById(Long id) {
        return aankoopbonRepository.findById(id);
    }

    public Aankoopbon createAankoopbon(Aankoopbon aankoopbon) {
        return aankoopbonRepository.save(aankoopbon);
    }

    public Aankoopbon updateAankoopbon(Long id, Aankoopbon aankoopbon) {
        if (aankoopbonRepository.existsById(id)) {
            aankoopbon.setId(id);
            return aankoopbonRepository.save(aankoopbon);
        } else {
            return null;
        }
    }

    public void deleteAankoopbon(Long id) {
        aankoopbonRepository.deleteById(id);
    }
}
