package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Levering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate leverDatum;
    private String leverancier;
    private String trackingCode;

    @OneToMany(mappedBy = "levering", cascade = CascadeType.ALL)
    private List<Aankoopbon> aankoopbonnen;

    // Constructors, getters, and setters

    public Levering() {
    }

    public Levering(LocalDate leverDatum, String leverancier, String trackingCode, List<Aankoopbon> aankoopbonnen) {
        this.leverDatum = leverDatum;
        this.leverancier = leverancier;
        this.trackingCode = trackingCode;
        this.aankoopbonnen = aankoopbonnen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLeverDatum() {
        return leverDatum;
    }

    public void setLeverDatum(LocalDate leverDatum) {
        this.leverDatum = leverDatum;
    }

    public String getLeverancier() {
        return leverancier;
    }

    public void setLeverancier(String leverancier) {
        this.leverancier = leverancier;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public List<Aankoopbon> getAankoopbonnen() {
        return aankoopbonnen;
    }

    public void setAankoopbonnen(List<Aankoopbon> aankoopbonnen) {
        this.aankoopbonnen = aankoopbonnen;
    }
}
