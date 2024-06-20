package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String straat;
    private String huisnummer;
    private String postcode;
    private String woonplaats;


    public Adres() {
    }
}
