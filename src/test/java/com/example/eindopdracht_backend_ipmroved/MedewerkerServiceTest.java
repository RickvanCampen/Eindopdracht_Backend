package com.example.eindopdracht_backend_ipmroved;

import com.example.eindopdracht_backend_ipmroved.entity.Medewerker;
import com.example.eindopdracht_backend_ipmroved.repository.MedewerkerRepository;
import com.example.eindopdracht_backend_ipmroved.service.MedewerkerService;
import com.example.eindopdracht_backend_ipmroved.Exception_Handling.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedewerkerServiceTest {

    @Mock
    private MedewerkerRepository medewerkerRepository;

    @InjectMocks
    private MedewerkerService medewerkerService;

    private Medewerker medewerker1;
    private Medewerker medewerker2;
    private Medewerker medewerker3;
    private Medewerker medewerker4;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        medewerker1 = new Medewerker(9L, "Bart Smit", "Backoffice medewerker", "bartsmit@gmail.com", "0640965555", "Bartje", "$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...", "NULL");
        medewerker2 = new Medewerker(10L, "Kees Kabouter", "Kassamedewerker", "keeskabouter@gmail.com", "0641952421", null, null, "NULL");
        medewerker3 = new Medewerker(11L, "Winnie de Pooh", "Administratief medewerker", "winniedepooh@gmail.com", "0611111111", null, null, "NULL");
        medewerker4 = new Medewerker(12L, "Arie Appelflap", "Fietsenmaker", "arieappelflap@gmail.com", "0641952421", null, null, "NULL");
    }

    @Test
    public void testGetAllMedewerkers() {
        when(medewerkerRepository.findAll()).thenReturn(List.of(medewerker1, medewerker2, medewerker3, medewerker4));

        List<Medewerker> medewerkers = medewerkerService.getAllMedewerkers();

        assertThat(medewerkers).hasSize(4);
        assertThat(medewerkers).containsExactlyInAnyOrder(medewerker1, medewerker2, medewerker3, medewerker4);
        verify(medewerkerRepository, times(1)).findAll();
    }

    @Test
    public void testGetMedewerkerById_existingId() {
        when(medewerkerRepository.findById(9L)).thenReturn(Optional.of(medewerker1));

        Medewerker foundMedewerker = medewerkerService.getMedewerkerById(9L);

        assertThat(foundMedewerker).isNotNull();
        assertThat(foundMedewerker.getNaam()).isEqualTo("Bart Smit");
        assertThat(foundMedewerker.getEmail()).isEqualTo("bartsmit@gmail.com");
        assertThat(foundMedewerker.getFunctie()).isEqualTo("Backoffice medewerker");
        assertThat(foundMedewerker.getTelefoonnummer()).isEqualTo("0640965555");
        assertThat(foundMedewerker.getGebruikersnaam()).isEqualTo("Bartje");
        assertThat(foundMedewerker.getWachtwoord()).isEqualTo("$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...");
        assertThat(foundMedewerker.getRol()).isEqualTo("NULL");
        verify(medewerkerRepository, times(1)).findById(9L);
    }

    @Test
    public void testGetMedewerkerByGebruikersnaam_existingUsername() {
        when(medewerkerRepository.findByGebruikersnaam("Bartje")).thenReturn(medewerker1);

        Medewerker foundMedewerker = medewerkerService.getMedewerkerByGebruikersnaam("Bartje");

        assertThat(foundMedewerker).isNotNull();
        assertThat(foundMedewerker.getNaam()).isEqualTo("Bart Smit");
        assertThat(foundMedewerker.getEmail()).isEqualTo("bartsmit@gmail.com");
        assertThat(foundMedewerker.getFunctie()).isEqualTo("Backoffice medewerker");
        assertThat(foundMedewerker.getTelefoonnummer()).isEqualTo("0640965555");
        assertThat(foundMedewerker.getGebruikersnaam()).isEqualTo("Bartje");
        assertThat(foundMedewerker.getWachtwoord()).isEqualTo("$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...");
        assertThat(foundMedewerker.getRol()).isEqualTo("NULL");
        verify(medewerkerRepository, times(1)).findByGebruikersnaam("Bartje");
    }

    @Test
    public void testCreateMedewerker() {
        when(medewerkerRepository.save(any(Medewerker.class))).thenReturn(medewerker1);

        Medewerker savedMedewerker = medewerkerService.createMedewerker(medewerker1);

        assertThat(savedMedewerker).isNotNull();
        assertThat(savedMedewerker.getNaam()).isEqualTo("Bart Smit");
        assertThat(savedMedewerker.getEmail()).isEqualTo("bartsmit@gmail.com");
        assertThat(savedMedewerker.getFunctie()).isEqualTo("Backoffice medewerker");
        assertThat(savedMedewerker.getTelefoonnummer()).isEqualTo("0640965555");
        assertThat(savedMedewerker.getGebruikersnaam()).isEqualTo("Bartje");
        assertThat(savedMedewerker.getWachtwoord()).isEqualTo("$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...");
        assertThat(savedMedewerker.getRol()).isEqualTo("NULL");
        verify(medewerkerRepository, times(1)).save(medewerker1);
    }




    @Test
    public void testUpdateMedewerker_nonExistingId() {
        Long medewerkerId = 13L;
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            medewerkerService.updateMedewerker(medewerkerId, medewerker1);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Medewerker with id " + medewerkerId + " not found");

        verify(medewerkerRepository, never()).save(any(Medewerker.class));
    }





    @Test
    public void testPromoteMedewerker_existingId() {
        Long medewerkerId = 9L;
        Medewerker promotedMedewerker = new Medewerker(medewerkerId, "Bart Smit", "Backoffice medewerker", "bartsmit@gmail.com", "0640965555", "Bartje", "$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...", "ADMIN");
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.of(medewerker1));
        when(medewerkerRepository.save(any(Medewerker.class))).thenReturn(promotedMedewerker);

        Medewerker result = medewerkerService.promoteMedewerker(medewerkerId);

        assertThat(result).isNotNull();
        assertThat(result.getRol()).isEqualTo("ADMIN");
        verify(medewerkerRepository, times(1)).findById(medewerkerId);
        verify(medewerkerRepository, times(1)).save(promotedMedewerker);
    }

    @Test
    public void testPromoteMedewerker_nonExistingId() {
        Long medewerkerId = 13L;
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            medewerkerService.promoteMedewerker(medewerkerId);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Medewerker with id " + medewerkerId + " not found");

        verify(medewerkerRepository, times(1)).findById(medewerkerId);
    }

    @Test
    public void testAssignRoleToMedewerker_existingId() {
        Long medewerkerId = 9L;
        String newRole = "MANAGER";
        Medewerker assignedRoleMedewerker = new Medewerker(medewerkerId, "Bart Smit", "Backoffice medewerker", "bartsmit@gmail.com", "0640965555", "Bartje", "$2a$10$AXNQl/ncBIpZc3dCTHbgP.PRaz.1LX6xaFPZr/yr5c3...", newRole);
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.of(medewerker1));
        when(medewerkerRepository.save(any(Medewerker.class))).thenReturn(assignedRoleMedewerker);

        Medewerker result = medewerkerService.assignRoleToMedewerker(medewerkerId, newRole);

        assertThat(result).isNotNull();
        assertThat(result.getRol()).isEqualTo(newRole);
        verify(medewerkerRepository, times(1)).findById(medewerkerId);
        verify(medewerkerRepository, times(1)).save(assignedRoleMedewerker);
    }

    @Test
    public void testAssignRoleToMedewerker_nonExistingId() {
        Long medewerkerId = 13L;
        String newRole = "MANAGER";
        when(medewerkerRepository.findById(medewerkerId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            medewerkerService.assignRoleToMedewerker(medewerkerId, newRole);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Medewerker with id " + medewerkerId + " not found");

        verify(medewerkerRepository, times(1)).findById(medewerkerId);
    }
}
