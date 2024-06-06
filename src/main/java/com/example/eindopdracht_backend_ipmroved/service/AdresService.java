package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Adres;
import com.example.eindopdracht_backend_ipmroved.repository.AdresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresService {

    private final AdresRepository adresRepository;

    @Autowired
    public AdresService(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    public List<Adres> getAllAdressen() {
        return adresRepository.findAll();
    }

    public Optional<Adres> getAdresById(Long id) {
        return adresRepository.findById(id);
    }

    public Adres createAdres(Adres adres) {
        return adresRepository.save(adres);
    }

    public Adres updateAdres(Long id, Adres adres) {
        if (adresRepository.existsById(id)) {
            adres.setId(id);
            return adresRepository.save(adres);
        } else {
            return null;
        }
    }

    public void deleteAdres(Long id) {
        adresRepository.deleteById(id);
    }
}
