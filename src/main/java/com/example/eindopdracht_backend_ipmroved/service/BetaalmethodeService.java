package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Betaalmethode;
import com.example.eindopdracht_backend_ipmroved.repository.BetaalmethodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetaalmethodeService {

    private final BetaalmethodeRepository betaalmethodeRepository;

    @Autowired
    public BetaalmethodeService(BetaalmethodeRepository betaalmethodeRepository) {
        this.betaalmethodeRepository = betaalmethodeRepository;
    }

    public List<Betaalmethode> getAllBetaalmethoden() {
        return betaalmethodeRepository.findAll();
    }

    public Optional<Betaalmethode> getBetaalmethodeById(Long id) {
        return betaalmethodeRepository.findById(id);
    }

    public Betaalmethode createBetaalmethode(Betaalmethode betaalmethode) {
        return betaalmethodeRepository.save(betaalmethode);
    }

    public Betaalmethode updateBetaalmethode(Long id, Betaalmethode betaalmethode) {
        if (betaalmethodeRepository.existsById(id)) {
            betaalmethode.setId(id);
            return betaalmethodeRepository.save(betaalmethode);
        } else {
            return null; // Return null or throw an exception to indicate that the betaalmethode with the given id doesn't exist
        }
    }

    public void deleteBetaalmethode(Long id) {
        betaalmethodeRepository.deleteById(id);
    }
}
