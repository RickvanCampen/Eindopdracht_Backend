package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.repository.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KlantService {

    private final KlantRepository klantRepository;

    @Autowired
    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public List<Klant> getAllKlanten() {
        return klantRepository.findAll();
    }

    public Optional<Klant> getKlantById(Long id) {
        return klantRepository.findById(id);
    }

    public Klant createKlant(Klant klant) {
        return klantRepository.save(klant);
    }

    public Klant updateKlant(Long id, Klant klant) {
        if (klantRepository.existsById(id)) {
            klant.setId(id);
            return klantRepository.save(klant);
        } else {
            return null;
        }
    }

    public void deleteKlant(Long id) {
        klantRepository.deleteById(id);
    }

    public Optional<Klant> upgradeKlantNaarPremium(Long id) {
        Optional<Klant> klantOpt = klantRepository.findById(id);
        if (klantOpt.isPresent()) {
            Klant klant = klantOpt.get();
            if (klant.getAankoopGeschiedenis().size() > 10) {
                klant.setPremium(true);
                klantRepository.save(klant);
                return Optional.of(klant);
            }
        }
        return Optional.empty();
    }

    public List<Klant> getAllPremiumKlanten() {
        return klantRepository.findAll().stream()
                .filter(Klant::isPremium)
                .collect(Collectors.toList());
    }
}
