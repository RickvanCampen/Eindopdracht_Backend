package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Factuur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate factuurDatum;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Betaalmethode betaalmethode;

    @OneToMany(mappedBy = "factuur", cascade = CascadeType.ALL)
    private List<Product> producten;

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFactuurDatum() {
        return factuurDatum;
    }

    public void setFactuurDatum(LocalDate factuurDatum) {
        this.factuurDatum = factuurDatum;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Betaalmethode getBetaalmethode() {
        return betaalmethode;
    }

    public void setBetaalmethode(Betaalmethode betaalmethode) {
        this.betaalmethode = betaalmethode;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    // Andere getters en setters
}
