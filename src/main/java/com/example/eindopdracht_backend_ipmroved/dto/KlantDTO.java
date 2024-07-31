package com.example.eindopdracht_backend_ipmroved.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KlantDTO {

    private Long id;
    private String voornaam;
    private String achternaam;
    private String email;
    private String telefoonnummer;
    private String adres;
    private String woonplaats;
    private String postcode;
    private boolean isPremium;
    private List<String> aankoopGeschiedenis;
}
