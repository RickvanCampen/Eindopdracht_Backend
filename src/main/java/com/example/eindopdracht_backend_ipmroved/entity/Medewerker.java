package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medewerker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String functie;
    private String email;
    private String telefoonnummer;

}
