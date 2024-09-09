package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.BicycleGarage;
import com.example.eindopdracht_backend_ipmroved.repository.BicycleGarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicycleGarageService {

    @Autowired
    private BicycleGarageRepository bicycleGarageRepository;

    public List<BicycleGarage> getAllBicycleGarages() {
        return bicycleGarageRepository.findAll();
    }

    public Optional<BicycleGarage> getBicycleGarageById(int id) {
        return bicycleGarageRepository.findById(id);
    }

    public BicycleGarage saveBicycleGarage(BicycleGarage bicycleGarage) {
        return bicycleGarageRepository.save(bicycleGarage);
    }

    public void deleteBicycleGarage(int id) {
        bicycleGarageRepository.deleteById(id);
    }
}
