package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;

@Entity
public class Fiets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merk;
    private String model;
    private int jaar;
    private String frameMateriaal;
    private String framemaat;
    private String kleur;

    @ManyToOne
    private Fietsgarage fietsgarage;

    // Constructor
    public Fiets() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public String getFrameMateriaal() {
        return frameMateriaal;
    }

    public void setFrameMateriaal(String frameMateriaal) {
        this.frameMateriaal = frameMateriaal;
    }

    public String getFramemaat() {
        return framemaat;
    }

    public void setFramemaat(String framemaat) {
        this.framemaat = framemaat;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public Fietsgarage getFietsgarage() {
        return fietsgarage;
    }

    public void setFietsgarage(Fietsgarage fietsgarage) {
        this.fietsgarage = fietsgarage;
    }
}
