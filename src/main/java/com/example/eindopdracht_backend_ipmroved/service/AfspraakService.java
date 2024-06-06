package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Afspraak;
import com.example.eindopdracht_backend_ipmroved.repository.AfspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfspraakService {

    private final AfspraakRepository afspraakRepository;

    @Autowired
    public AfspraakService(AfspraakRepository afspraakRepository) {
        this.afspraakRepository = afspraakRepository;
    }

    public List<Afspraak> getAllAfspraken() {
        return afspraakRepository.findAll();
    }

    public Optional<Afspraak> getAfspraakById(Long id) {
        return afspraakRepository.findById(id);
    }

    public Afspraak createAfspraak(Afspraak afspraak) {
        return afspraakRepository.save(afspraak);
    }

    public Afspraak updateAfspraak(Long id, Afspraak afspraak) {
        if (afspraakRepository.existsById(id)) {
            afspraak.setId(id);
            return afspraakRepository.save(afspraak);
        } else {
            return null;
        }
    }

    public void deleteAfspraak(Long id) {
        afspraakRepository.deleteById(id);
    }
}
