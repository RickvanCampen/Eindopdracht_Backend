package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gebruikersnaam;
    private String wachtwoord;

    @OneToOne
    @JoinColumn(name = "medewerker_id", referencedColumnName = "id")
    private Medewerker medewerker;

    // Geen noodzaak voor expliciete constructor, getters en setters met Lombok
}
