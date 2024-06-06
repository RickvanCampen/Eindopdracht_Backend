package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Checkbeurt;
import com.example.eindopdracht_backend_ipmroved.repository.CheckbeurtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckbeurtService {

    private final CheckbeurtRepository checkbeurtRepository;

    @Autowired
    public CheckbeurtService(CheckbeurtRepository checkbeurtRepository) {
        this.checkbeurtRepository = checkbeurtRepository;
    }

    public List<Checkbeurt> getAllCheckbeurten() {
        return checkbeurtRepository.findAll();
    }

    public Optional<Checkbeurt> getCheckbeurtById(Long id) {
        return checkbeurtRepository.findById(id);
    }

    public Checkbeurt createCheckbeurt(Checkbeurt checkbeurt) {
        return checkbeurtRepository.save(checkbeurt);
    }

    public Checkbeurt updateCheckbeurt(Long id, Checkbeurt checkbeurt) {
        if (checkbeurtRepository.existsById(id)) {
            checkbeurt.setId(id);
            return checkbeurtRepository.save(checkbeurt);
        } else {
            return null; // Return null or throw an exception to indicate that the checkbeurt with the given id doesn't exist
        }
    }

    public void deleteCheckbeurt(Long id) {
        checkbeurtRepository.deleteById(id);
    }
}
