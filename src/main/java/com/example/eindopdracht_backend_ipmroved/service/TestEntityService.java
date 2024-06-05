package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.Entity.TestEntity;
import com.example.eindopdracht_backend_ipmroved.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestEntityService {
    @Autowired
    private TestEntityRepository testEntityRepository;

    public List<TestEntity> getAllTestEntities() {
        return testEntityRepository.findAll();
    }

    public TestEntity getTestEntityById(Long id) {
        return testEntityRepository.findById(id).orElse(null);
    }

    public TestEntity saveTestEntity(TestEntity testEntity) {
        return testEntityRepository.save(testEntity);
    }

    public void deleteTestEntity(Long id) {
        testEntityRepository.deleteById(id);
    }
}
