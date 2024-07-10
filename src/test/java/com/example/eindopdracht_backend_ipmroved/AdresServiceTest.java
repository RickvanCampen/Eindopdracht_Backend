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
        adres1 = new Adres(4L, "Molenstraat", "2", "2345 BC", "Rotterdam");
        adres2 = new Adres(8L, "Havenstraat", "3", "3456 CD", "Utrecht");
    }

    @Test
    public void testGetAllAdressen() {
        when(adresRepository.findAll()).thenReturn(Arrays.asList(adres1, adres2));

        List<Adres> adressen = adresService.getAllAdressen();

        assertThat(adressen).hasSize(2);
        assertThat(adressen).containsExactlyInAnyOrder(adres1, adres2);
        verify(adresRepository, times(1)).findAll();
    }

    @Test
    public void testGetAdresById_existingId() {
        when(adresRepository.findById(4L)).thenReturn(Optional.of(adres1));

        Optional<Adres> foundAdres = adresService.getAdresById(4L);

        assertThat(foundAdres).isPresent();
        assertThat(foundAdres.get().getPostcode()).isEqualTo("2345 BC");
        verify(adresRepository, times(1)).findById(4L);
    }

    @Test
    public void testCreateAdres() {
        when(adresRepository.save(any(Adres.class))).thenReturn(adres1);

        Adres savedAdres = adresService.createAdres(adres1);

        assertThat(savedAdres.getWoonplaats()).isEqualTo("Rotterdam");
        verify(adresRepository, times(1)).save(adres1);
    }

    @Test
    public void testDeleteAdres() {
        Long adresId = 1L;
        doNothing().when(adresRepository).deleteById(adresId);

        adresService.deleteAdres(adresId);

        verify(adresRepository, times(1)).deleteById(adresId);
    }
}
