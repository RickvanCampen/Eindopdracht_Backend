package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voornaam;
    private String achternaam;
    private String email;
    private String telefoonnummer;
    private String adres;
    private String woonplaats;
    private String postcode;

}
