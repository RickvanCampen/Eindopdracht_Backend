package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.entity.Checkbeurt;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import com.example.eindopdracht_backend_ipmroved.repository.CheckbeurtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Checkbeurt getCheckbeurtById(Long id) {
        return checkbeurtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Checkbeurt met id " + id + " niet gevonden"));
    }

    public Checkbeurt createCheckbeurt(Checkbeurt checkbeurt) {
        return checkbeurtRepository.save(checkbeurt);
    }

    public Checkbeurt updateCheckbeurt(Long id, Checkbeurt checkbeurt) {
        if (!checkbeurtRepository.existsById(id)) {
            throw new ResourceNotFoundException("Checkbeurt met id " + id + " niet gevonden");
        }
        checkbeurt.setId(id);
        return checkbeurtRepository.save(checkbeurt);
    }

    public void deleteCheckbeurt(Long id) {
        if (!checkbeurtRepository.existsById(id)) {
            throw new ResourceNotFoundException("Checkbeurt met id " + id + " niet gevonden");
        }
        checkbeurtRepository.deleteById(id);
    }
}
