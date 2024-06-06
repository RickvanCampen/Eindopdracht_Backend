package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fietsgarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String adres;
    private String woonplaats;
    private String postcode;

    @OneToMany(mappedBy = "fietsgarage", cascade = CascadeType.ALL)
    private List<Fiets> fietsen;

    // Constructors, getters, and setters

    public Fietsgarage() {
    }

    public Fietsgarage(String naam, String adres, String woonplaats, String postcode, List<Fiets> fietsen) {
        this.naam = naam;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.postcode = postcode;
        this.fietsen = fietsen;
    }

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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public List<Fiets> getFietsen() {
        return fietsen;
    }

    public void setFietsen(List<Fiets> fietsen) {
        this.fietsen = fietsen;
    }
}
