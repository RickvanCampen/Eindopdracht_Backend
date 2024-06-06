package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fiets_id")
    private Fiets fiets;

    @ManyToOne
    @JoinColumn(name = "medewerker_id")
    private Medewerker medewerker;

    @ManyToOne
    @JoinColumn(name = "checkbeurt_id")
    private Checkbeurt checkbeurt;

    private String opmerkingen;

    private Date datum;

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fiets getFiets() {
        return fiets;
    }

    public void setFiets(Fiets fiets) {
        this.fiets = fiets;
    }

    public Medewerker getMedewerker() {
        return medewerker;
    }

    public void setMedewerker(Medewerker medewerker) {
        this.medewerker = medewerker;
    }

    public Checkbeurt getCheckbeurt() {
        return checkbeurt;
    }

    public void setCheckbeurt(Checkbeurt checkbeurt) {
        this.checkbeurt = checkbeurt;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
