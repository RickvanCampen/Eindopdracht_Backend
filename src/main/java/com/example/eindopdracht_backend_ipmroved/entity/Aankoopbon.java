package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Aankoopbon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;

    private double totaalBedrag;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Levering levering;

    @OneToMany(mappedBy = "aankoopbon", cascade = CascadeType.ALL)
    private List<BestellingOnderdeel> bestellingen;

    // Constructors
    public Aankoopbon() {}

    public Aankoopbon(LocalDate datum, double totaalBedrag, Klant klant, Levering levering, List<BestellingOnderdeel> bestellingen) {
        this.datum = datum;
        this.totaalBedrag = totaalBedrag;
        this.klant = klant;
        this.levering = levering;
        this.bestellingen = bestellingen;
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

    public double getTotaalBedrag() {
        return totaalBedrag;
    }

    public void setTotaalBedrag(double totaalBedrag) {
        this.totaalBedrag = totaalBedrag;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Levering getLevering() {
        return levering;
    }

    public void setLevering(Levering levering) {
        this.levering = levering;
    }

    public List<BestellingOnderdeel> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<BestellingOnderdeel> bestellingen) {
        this.bestellingen = bestellingen;
    }
}
