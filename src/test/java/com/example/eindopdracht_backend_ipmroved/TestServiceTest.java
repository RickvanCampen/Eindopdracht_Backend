package com.example.eindopdracht_backend_ipmroved;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    @Mock
    private TestEntityRepository testRepository;

    @InjectMocks
    private TestService testService;

    private TestEntity testEntity;

    @BeforeEach
    void setUp() {
        testEntity = new TestEntity();
        testEntity.setId(1L);
        testEntity.setName("Test Name");
    }

    @Test
    void testGetTestEntityById() {
        when(testRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        TestEntity found = testService.getTestEntityById(1L);

        assertThat(found.getName()).isEqualTo("Test Name");
        verify(testRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveTestEntity() {
        when(testRepository.save(testEntity)).thenReturn(testEntity);

        TestEntity saved = testService.saveTestEntity(testEntity);

        assertThat(saved.getName()).isEqualTo("Test Name");
        verify(testRepository, times(1)).save(testEntity);
    }

    // Voeg hier meer tests toe voor andere methoden van TestService
}
