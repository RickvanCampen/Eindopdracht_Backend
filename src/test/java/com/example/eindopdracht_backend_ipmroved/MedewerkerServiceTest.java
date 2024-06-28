package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.repository.MedewerkerRepository;
import com.example.eindopdracht_backend_ipmroved.service.MedewerkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedewerkerServiceTest {

    @Mock
    private MedewerkerRepository medewerkerRepository;

    @InjectMocks
    private MedewerkerService medewerkerService;

    private Medewerker medewerker1;
    private Medewerker medewerker2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        medewerker1 = new Medewerker(1L, "Bart Smit", "baas", "bartsmit@gmail.com", "0640965555");
        medewerker2 = new Medewerker(2L, "Kees Kabouter", "fietsenmaker", "keeskabouter@gmail.com", "0641952421");
    }

    @Test
    public void testGetAllMedewerkers() {
        // Arrange
        when(medewerkerRepository.findAll()).thenReturn(Arrays.asList(medewerker1, medewerker2));

        // Act
        List<Medewerker> medewerkers = medewerkerService.getAllMedewerkers();

        // Assert
        assertThat(medewerkers).hasSize(2);
        assertThat(medewerkers).containsExactlyInAnyOrder(medewerker1, medewerker2);
        verify(medewerkerRepository, times(1)).findAll();
    }

    @Test
    public void testGetMedewerkerById_existingId() {
        // Arrange
        when(medewerkerRepository.findById(1L)).thenReturn(Optional.of(medewerker1));

        // Act
        Optional<Medewerker> foundMedewerker = medewerkerService.getMedewerkerById(1L);

        // Assert
        assertThat(foundMedewerker).isPresent();
        assertThat(foundMedewerker.get().getNaam()).isEqualTo("Bart Smit");
        verify(medewerkerRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateMedewerker() {
        // Arrange
        when(medewerkerRepository.save(any(Medewerker.class))).thenReturn(medewerker1);

        // Act
        Medewerker savedMedewerker = medewerkerService.createMedewerker(medewerker1);

        // Assert
        assertThat(savedMedewerker.getNaam()).isEqualTo("Bart Smit");
        verify(medewerkerRepository, times(1)).save(medewerker1);
    }

    @Test
    public void testUpdateMedewerker_existingId() {
        // Arrange
        Long medewerkerId = 1L;
        Medewerker updatedMedewerker = new Medewerker(medewerkerId, "Updated Bart Smit", "updated baas", "updatedbartsmit@gmail.com", "0640965555");
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.of(medewerker1));
        when(medewerkerRepository.save(any(Medewerker.class))).thenReturn(updatedMedewerker);

        // Act
        Medewerker result = medewerkerService.updateMedewerker(medewerkerId, updatedMedewerker);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNaam()).isEqualTo("Updated Bart Smit");
        assertThat(result.getEmail()).isEqualTo("updatedbartsmit@gmail.com");
        verify(medewerkerRepository, times(1)).findById(medewerkerId);
        verify(medewerkerRepository, times(1)).save(updatedMedewerker);
    }

    @Test
    public void testDeleteMedewerker() {
        // Arrange
        Long medewerkerId = 1L;
        doNothing().when(medewerkerRepository).deleteById(medewerkerId);

        // Act
        medewerkerService.deleteMedewerker(medewerkerId);

        // Assert
        verify(medewerkerRepository, times(1)).deleteById(medewerkerId);
    }
}
