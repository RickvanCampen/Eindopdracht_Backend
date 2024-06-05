package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.model.TestEntity;
import com.example.eindopdracht_backend_ipmroved.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestEntityRepository testRepository;

    public TestEntity getTestEntityById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public TestEntity saveTestEntity(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }
}