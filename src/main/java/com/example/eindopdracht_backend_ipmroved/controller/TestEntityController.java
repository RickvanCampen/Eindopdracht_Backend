package com.example.eindopdracht_backend_ipmroved.controller;

import com.example.eindopdracht_backend_ipmroved.model.TestEntity;
import com.example.eindopdracht_backend_ipmroved.service.TestEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-entities")
public class TestEntityController {
    @Autowired
    private TestEntityService testEntityService;

    @GetMapping
    public List<TestEntity> getAllTestEntities() {
        return testEntityService.getAllTestEntities();
    }

    @GetMapping("/{id}")
    public TestEntity getTestEntityById(@PathVariable Long id) {
        return testEntityService.getTestEntityById(id);
    }

    @PostMapping
    public TestEntity createTestEntity(@RequestBody TestEntity testEntity) {
        return testEntityService.saveTestEntity(testEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteTestEntity(@PathVariable Long id) {
        testEntityService.deleteTestEntity(id);
    }
}
