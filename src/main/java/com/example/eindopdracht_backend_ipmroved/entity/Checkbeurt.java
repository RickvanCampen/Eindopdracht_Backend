package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Checkbeurt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private LocalDate datum;

    private String opmerkingen;

    private boolean isVoltooid;

    @ManyToOne
    private Fiets fiets;

    @ManyToOne
    private Medewerker medewerker;

    @OneToMany(mappedBy = "checkbeurt", cascade = CascadeType.ALL)
    private List<Reparatie> reparaties;

    // Constructors, getters, and setters
    public Checkbeurt() {
    }

    public Checkbeurt(String naam, LocalDate datum, String opmerkingen, boolean isVoltooid, Fiets fiets, Medewerker medewerker) {
        this.naam = naam;
        this.datum = datum;
        this.opmerkingen = opmerkingen;
        this.isVoltooid = isVoltooid;
        this.fiets = fiets;
        this.medewerker = medewerker;
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public boolean isVoltooid() {
        return isVoltooid;
    }

    public void setVoltooid(boolean voltooid) {
        isVoltooid = voltooid;
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

    public List<Reparatie> getReparaties() {
        return reparaties;
    }

    public void setReparaties(List<Reparatie> reparaties) {
        this.reparaties = reparaties;
    }

    @Override
    public String toString() {
        return "Checkbeurt{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", datum=" + datum +
                ", opmerkingen='" + opmerkingen + '\'' +
                ", isVoltooid=" + isVoltooid +
                ", fiets=" + fiets +
                ", medewerker=" + medewerker +
                '}';
    }
}
