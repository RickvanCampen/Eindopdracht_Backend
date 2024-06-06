package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
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

    // Constructors, getters, and setters
    public Afspraak() {
    }

    public Afspraak(LocalDate datum, LocalTime begintijd, LocalTime eindtijd, String beschrijving, String locatie, String status) {
        this.datum = datum;
        this.begintijd = begintijd;
        this.eindtijd = eindtijd;
        this.beschrijving = beschrijving;
        this.locatie = locatie;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getBegintijd() {
        return begintijd;
    }

    public void setBegintijd(LocalTime begintijd) {
        this.begintijd = begintijd;
    }

    public LocalTime getEindtijd() {
        return eindtijd;
    }

    public void setEindtijd(LocalTime eindtijd) {
        this.eindtijd = eindtijd;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Afspraak{" +
                "id=" + id +
                ", datum=" + datum +
                ", begintijd=" + begintijd +
                ", eindtijd=" + eindtijd +
                ", beschrijving='" + beschrijving + '\'' +
                ", locatie='" + locatie + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
