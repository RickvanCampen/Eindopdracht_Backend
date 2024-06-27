package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;

    // Lombok genereert een constructor met alle velden als parameters
    public Adres(Long id, String straat, String huisnummer, String postcode, String woonplaats) {
        this.id = id;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }
}
