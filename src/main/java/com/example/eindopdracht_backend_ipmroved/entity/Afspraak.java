package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Afspraak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;
    private LocalTime begintijd;
    private LocalTime eindtijd;
    private String beschrijving;
    private String locatie;
    private String status;

    // No-argument constructor
    public Afspraak() {
    }

    // Constructor with parameters
    public Afspraak(LocalDate datum, LocalTime begintijd, LocalTime eindtijd, String beschrijving, String locatie, String status) {
        this.datum = datum;
        this.begintijd = begintijd;
        this.eindtijd = eindtijd;
        this.beschrijving = beschrijving;
        this.locatie = locatie;
        this.status = status;
    }
}
