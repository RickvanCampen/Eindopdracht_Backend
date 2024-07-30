package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Klant;
import com.example.eindopdracht_backend_ipmroved.repository.KlantRepository;
import com.example.eindopdracht_backend_ipmroved.service.KlantService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class KlantServiceTest {

    @Mock
    private KlantRepository klantRepository;

    @InjectMocks
    private KlantService klantService;

    private Klant klant1;
    private Klant klant2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        klant1 = new Klant(1L, "Anne", "Slager", "slager@example.com", "0654321098", "Havenstraat 3", "Utrecht", "3456 CD");
        klant2 = new Klant(2L, "Piet", "Molensteen", "molensteen@example.com", "0687654321", "Molenstraat 2", "Rotterdam", "2345 BC");
    }


    @Test
    public void testGetAllKlanten() {
        when(klantRepository.findAll()).thenReturn(Arrays.asList(klant1, klant2));

        List<Klant> klanten = klantService.getAllKlanten();

        assertThat(klanten).hasSize(2);
        assertThat(klanten).containsExactlyInAnyOrder(klant1, klant2);
        verify(klantRepository, times(1)).findAll();
    }

    @Test
    public void testGetKlantById_existingId() {
        when(klantRepository.findById(1L)).thenReturn(Optional.of(klant1));

        Optional<Klant> foundKlant = klantService.getKlantById(1L);

        assertThat(foundKlant).isPresent();
        assertThat(foundKlant.get().getAchternaam()).isEqualTo("Slager");
        verify(klantRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateKlant() {
        when(klantRepository.save(any(Klant.class))).thenReturn(klant1);

        Klant savedKlant = klantService.createKlant(klant1);

        assertThat(savedKlant.getVoornaam()).isEqualTo("Anne");
        verify(klantRepository, times(1)).save(klant1);
    }

    @Test
    public void testUpdateKlant_existingId() {
        when(klantRepository.existsById(1L)).thenReturn(true);
        when(klantRepository.save(any(Klant.class))).thenReturn(klant1);

        klant1.setAdres("Nieuwe Havenstraat 3");
        Klant updatedKlant = klantService.updateKlant(1L, klant1);

        assertThat(updatedKlant).isNotNull();
        assertThat(updatedKlant.getAdres()).isEqualTo("Nieuwe Havenstraat 3");
        verify(klantRepository, times(1)).existsById(1L);
        verify(klantRepository, times(1)).save(klant1);
    }

    @Test
    public void testUpdateKlant_nonExistingId() {
        when(klantRepository.existsById(anyLong())).thenReturn(false);

        Klant result = klantService.updateKlant(1L, klant1);

        assertThat(result).isNull();
        verify(klantRepository, times(1)).existsById(1L);
        verify(klantRepository, never()).save(any(Klant.class));
    }

    @Test
    public void testDeleteKlant() {
        Long klantId = 1L;
        doNothing().when(klantRepository).deleteById(klantId);

        klantService.deleteKlant(klantId);

        verify(klantRepository, times(1)).deleteById(klantId);
    }
}
