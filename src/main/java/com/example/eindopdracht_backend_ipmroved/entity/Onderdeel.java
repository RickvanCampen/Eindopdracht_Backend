package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;

@Entity
public class Onderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private double prijs;
    private String beschrijving;
    private int aantalOpVoorraad;

    // Constructors
    public Onderdeel() {
    }

    public Onderdeel(String naam, double prijs, String beschrijving, int aantalOpVoorraad) {
        this.naam = naam;
        this.prijs = prijs;
        this.beschrijving = beschrijving;
        this.aantalOpVoorraad = aantalOpVoorraad;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getAantalOpVoorraad() {
        return aantalOpVoorraad;
    }

    public void setAantalOpVoorraad(int aantalOpVoorraad) {
        this.aantalOpVoorraad = aantalOpVoorraad;
    }
}
