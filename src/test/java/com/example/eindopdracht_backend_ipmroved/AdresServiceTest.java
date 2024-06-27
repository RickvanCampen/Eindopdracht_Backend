package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Adres;
import com.example.eindopdracht_backend_ipmroved.repository.AdresRepository;
import com.example.eindopdracht_backend_ipmroved.service.AdresService;
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

public class AdresServiceTest {

    @Mock
    private AdresRepository adresRepository;

    @InjectMocks
    private AdresService adresService;

    private Adres adres1;
    private Adres adres2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        adres1 = new Adres(1L, "Null", "Null", "1234 AB", "Voorbeeldstad");
        adres2 = new Adres(4L, "Null", "Null", "5678 CD", "Andereplaats");
    }

    @Test
    public void testGetAllAdressen() {
        // Arrange
        when(adresRepository.findAll()).thenReturn(Arrays.asList(adres1, adres2));

        // Act
        List<Adres> adressen = adresService.getAllAdressen();

        // Assert
        assertThat(adressen).hasSize(2);
        assertThat(adressen).containsExactlyInAnyOrder(adres1, adres2);
        verify(adresRepository, times(1)).findAll();
    }

    @Test
    public void testGetAdresById_existingId() {
        // Arrange
        when(adresRepository.findById(1L)).thenReturn(Optional.of(adres1));

        // Act
        Optional<Adres> foundAdres = adresService.getAdresById(1L);

        // Assert
        assertThat(foundAdres).isPresent();
        assertThat(foundAdres.get().getPostcode()).isEqualTo("1234 AB");
        verify(adresRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAdres() {
        // Arrange
        when(adresRepository.save(any(Adres.class))).thenReturn(adres1);

        // Act
        Adres savedAdres = adresService.createAdres(adres1);

        // Assert
        assertThat(savedAdres.getWoonplaats()).isEqualTo("Voorbeeldstad");
        verify(adresRepository, times(1)).save(adres1);
    }

    // Voeg meer tests toe voor andere methoden zoals updateAdres, deleteAdres, etc.
}
