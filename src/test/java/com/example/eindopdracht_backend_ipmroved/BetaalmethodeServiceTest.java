package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Betaalmethode;
import com.example.eindopdracht_backend_ipmroved.repository.BetaalmethodeRepository;
import com.example.eindopdracht_backend_ipmroved.service.BetaalmethodeService;
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

public class BetaalmethodeServiceTest {

    @Mock
    private BetaalmethodeRepository betaalmethodeRepository;

    @InjectMocks
    private BetaalmethodeService betaalmethodeService;

    private Betaalmethode betaalmethode1;
    private Betaalmethode betaalmethode2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        betaalmethode1 = new Betaalmethode(1L, "iDEAL", "Een populaire online betaalmethode", true);
        betaalmethode2 = new Betaalmethode(2L, "Creditcard", "Een betaalmethode met creditcard", true);
    }

    @Test
    public void testGetAllBetaalmethoden() {
        // Arrange
        when(betaalmethodeRepository.findAll()).thenReturn(Arrays.asList(betaalmethode1, betaalmethode2));

        // Act
        List<Betaalmethode> betaalmethoden = betaalmethodeService.getAllBetaalmethoden();

        // Assert
        assertThat(betaalmethoden).hasSize(2);
        assertThat(betaalmethoden).containsExactlyInAnyOrder(betaalmethode1, betaalmethode2);
        verify(betaalmethodeRepository, times(1)).findAll();
    }

    @Test
    public void testGetBetaalmethodeById_existingId() {
        // Arrange
        when(betaalmethodeRepository.findById(1L)).thenReturn(Optional.of(betaalmethode1));

        // Act
        Optional<Betaalmethode> foundBetaalmethode = betaalmethodeService.getBetaalmethodeById(1L);

        // Assert
        assertThat(foundBetaalmethode).isPresent();
        assertThat(foundBetaalmethode.get().getNaam()).isEqualTo("iDEAL");
        verify(betaalmethodeRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateBetaalmethode() {
        // Arrange
        when(betaalmethodeRepository.save(any(Betaalmethode.class))).thenReturn(betaalmethode1);

        // Act
        Betaalmethode savedBetaalmethode = betaalmethodeService.createBetaalmethode(betaalmethode1);

        // Assert
        assertThat(savedBetaalmethode.getNaam()).isEqualTo("iDEAL");
        verify(betaalmethodeRepository, times(1)).save(betaalmethode1);
    }

    @Test
    public void testDeleteBetaalmethode() {
        // Arrange
        Long betaalmethodeId = 1L;
        doNothing().when(betaalmethodeRepository).deleteById(betaalmethodeId);

        // Act
        betaalmethodeService.deleteBetaalmethode(betaalmethodeId);

        // Assert
        verify(betaalmethodeRepository, times(1)).deleteById(betaalmethodeId);
    }
}
