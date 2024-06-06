package com.example.eindopdracht_backend_ipmroved.entity;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private double prijs;

    @ManyToOne
    @JoinColumn(name = "factuur_id")
    private Factuur factuur;

    // constructors, getters en setters

    // Constructor
    public Product() {
    }

    // Constructor met naam en prijs
    public Product(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    // Getters en setters
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

    public Factuur getFactuur() {
        return factuur;
    }

    public void setFactuur(Factuur factuur) {
        this.factuur = factuur;
    }
}
